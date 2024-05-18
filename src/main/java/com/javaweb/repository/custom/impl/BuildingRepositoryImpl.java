package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.utils.ObjectUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        StringBuilder finalSql = new StringBuilder(buildQueryFilter(buildingSearchRequest))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(finalSql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {
        String sql = buildQueryFilter(buildingSearchRequest);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList().size();
    }

    private String buildQueryFilter(BuildingSearchRequest buildingSearchRequest) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");

        StringBuilder whereSql = new StringBuilder("WHERE 1 = 1 ");
        StringBuilder joinSql = new StringBuilder();
        querySqlJoin(buildingSearchRequest, joinSql);
        querySqlWhereNomal(buildingSearchRequest, whereSql);
        querySqlWhereSpecial(buildingSearchRequest, whereSql);

        sql.append(joinSql);
        sql.append(whereSql);
        sql.append("GROUP BY b.id");

        return sql.toString().trim();
    }

    private void querySqlJoin(BuildingSearchRequest buildingSearchRequest, StringBuilder sb){
        Long staffId = buildingSearchRequest.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("INNER JOIN assignmentbuilding a ON a.buildingid = b.id ");
        }
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        Long areaTo = buildingSearchRequest.getAreaTo();
        if(ObjectUtil.checkObject(areaFrom) || ObjectUtil.checkObject(areaTo)){
            sb.append("INNER JOIN rentarea r ON r.buildingid = b.id ");
        }
    }
    private void querySqlWhereNomal(BuildingSearchRequest buildingSearchRequest, StringBuilder sb){
        try{
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                Object value = item.get(buildingSearchRequest);
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")){
                    if(ObjectUtil.checkObject(value)){
                        if(ObjectUtil.isNumber(value)){
                            sb.append("AND b." + fieldName + " = " + value + " ");
                        }else {
                            sb.append("AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void querySqlWhereSpecial(BuildingSearchRequest buildingSearchBuilder, StringBuilder sb){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("AND a.staffid = " + staffId + " ");
        }
        Long areaFrom = buildingSearchBuilder.getAreaFrom();
        if(ObjectUtil.checkObject(areaFrom)){
            sb.append("AND r.value >= " + areaFrom + " ");
        }
        Long areaTo = buildingSearchBuilder.getAreaTo();
        if(ObjectUtil.checkObject(areaTo)){
            sb.append("AND r.value <= " + areaTo + " ");
        }
        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if(ObjectUtil.checkObject(rentPriceFrom)){
            sb.append("AND b.rentprice >= " + rentPriceFrom + " ");
        }
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if(ObjectUtil.checkObject(rentPriceTo)){
            sb.append("AND b.rentprice <= " + rentPriceTo + " ");
        }
        List<String> typeCodes = buildingSearchBuilder.getTypeCode();
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("AND b.type = '").append(String.join(",", typeCodes)).append("' ");
        }
    }
}
