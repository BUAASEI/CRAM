package cn.edu.buaa.rec.service.impl;

import cn.edu.buaa.rec.dao.BusinessMapper;
import cn.edu.buaa.rec.dao.BusinessRoleDataMapper;
import cn.edu.buaa.rec.dao.DataMapper;
import cn.edu.buaa.rec.dao.RoleMapper;
import cn.edu.buaa.rec.model.Business;
import cn.edu.buaa.rec.model.BusinessRoleData;
import cn.edu.buaa.rec.model.Data;
import cn.edu.buaa.rec.model.Role;
import cn.edu.buaa.rec.service.BusinessRoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("BusinessRoleDataService")
public class BusinessRoleDataServiceImpl implements BusinessRoleDataService {

    @Autowired
    private BusinessRoleDataMapper businessRoleDataMapper;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private RoleMapper rolemapper;
    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<BusinessRoleData> getBusinessRoleDataByRoleIds(List<Long> rIds) {
        if (rIds == null && rIds.size() == 0) {
            return null;
        }

//        List<BusinessRoleData> listBRD = new LinkedList<>();
//        for (Long rId : rIds){
//            List<BusinessRoleData> bus= businessRoleDataMapper.selectBusinessRoleDataByRoleId(rId);
//            listBRD.addAll(bus);
//        }
//

        List<BusinessRoleData> listBRD = businessRoleDataMapper.selectBusinessRoleDataByRoleIds(rIds);
        return listBRD;
    }

    @Override
    public List<Map<String, Object>> getBusinessForm(List<BusinessRoleData> bRDId) {
        if (bRDId == null || bRDId.size() == 0) {
            return null;
        }

        List<Map<String, Object>> bRDForms = new LinkedList<>();
        for (BusinessRoleData brd : bRDId) {
            Map<String, Object> businessForm = new HashMap<>();
            Business business = businessMapper.selectBusiness(brd.getBusinessId());
            Role role = rolemapper.selectById(brd.getRoleId());
            Data data = dataMapper.selectById(brd.getDataId());
            businessForm.put("id", brd.getId());
            businessForm.put("businessName", business.getName());
            businessForm.put("businessDes", business.getDescription());
            businessForm.put("roleId", role.getId());
            businessForm.put("roleName", role.getName());
            businessForm.put("dataId", data.getId());
            businessForm.put("dataName", data.getName());
            bRDForms.add(businessForm);
        }


        return bRDForms;
    }
}
