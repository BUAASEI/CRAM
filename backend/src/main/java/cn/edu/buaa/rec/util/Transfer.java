package cn.edu.buaa.rec.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: suruo
 * @Email: csuruo@gmail.com
 * @Date: Created on 上午2:46 2018/03/11
 * @Modified by:
 */

public class Transfer {
    Map<String, Object> root;
    Map<String, Object> rootBody;
    List<Object> body;
    List<Object> children;


    private void init() {
        root = new HashMap<String, Object>();
        body = new ArrayList<>();
        rootBody = new HashMap<>();
        children = new ArrayList<>();
        root.put("data", body);
        body.add(rootBody);

    }
    private void initRootBody() {
        rootBody.put("title", "modelElements");
        rootBody.put("expand", true);
        rootBody.put("children", children);
    }
    public void addUseCase(Usecase usecase) {
        children.add(usecase.toMap());
    }

    public Transfer() {
        init();
        initRootBody();
    }

    public Transfer(Usecase usecases) {
        init();
        initRootBody();
        children.add(usecases.toMap());
    }


    class Usecase {
        private String title;
        private boolean expend = true;

        private Map<String, String> map = new HashMap<>();


        public Map<String, Object> toMap() {
            Map<String, Object> map = new HashMap<>();
            return map;
        }
    }


}
