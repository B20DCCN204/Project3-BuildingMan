package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import com.javaweb.utils.ObjectUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<CustomerEntity> findAll(CustomerSearchRequest customerSearchRequest, Pageable pageable) {
        StringBuilder finalSql = new StringBuilder(buildQueryFilter(customerSearchRequest))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(finalSql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(CustomerSearchRequest customerSearchRequest) {
        String sql = buildQueryFilter(customerSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList().size();
    }

    private String buildQueryFilter(CustomerSearchRequest customerSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c ");

        StringBuilder whereSql = new StringBuilder("WHERE 1 = 1 ");
        StringBuilder joinSql = new StringBuilder();
        querySqlJoin(customerSearchRequest, joinSql);
        querySqlWhereNomal(customerSearchRequest, whereSql);
        querySqlWhereSpecial(customerSearchRequest, whereSql);

        sql.append(joinSql);
        sql.append(whereSql);
        sql.append("GROUP BY c.id");

        return sql.toString().trim();
    }

    private void querySqlJoin(CustomerSearchRequest customerSearchRequest, StringBuilder sb){
        Long staffId = customerSearchRequest.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("INNER JOIN assignmentcustomer a ON a.customerid = c.id ");
        }
    }
    private void querySqlWhereNomal(CustomerSearchRequest customerSearchRequest, StringBuilder sb){
        try{
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                Object value = item.get(customerSearchRequest);
                if(!fieldName.equals("staffId")){
                    if(ObjectUtil.checkObject(value)){
                        if(ObjectUtil.isNumber(value)){
                            sb.append("AND c." + fieldName + " = " + value + " ");
                        }else {
                            sb.append("AND c." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void querySqlWhereSpecial(CustomerSearchRequest customerSearchRequest, StringBuilder sb){
        Long staffId = customerSearchRequest.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("AND a.staffid = " + staffId + " ");
        }
    }
}
