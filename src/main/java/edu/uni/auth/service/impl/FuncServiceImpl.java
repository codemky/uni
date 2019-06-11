package edu.uni.auth.service.impl;

import edu.uni.auth.bean.*;
import edu.uni.auth.mapper.FuncMapper;
import edu.uni.auth.mapper.FuncModuleMapper;
import edu.uni.auth.mapper.RoleMapper;
import edu.uni.auth.service.FuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author 何亮
 */
@Service
public class FuncServiceImpl implements FuncService {
    @Autowired
    private FuncModuleMapper funcModuleMapper;

    @Autowired
    private FuncMapper funcMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取角色功能点菜单
     * @param roleId
     * @return
     */
    @Override
    public List<Node> getRoleFuncMenu(long roleId){
        List<Node> menu = new LinkedList<>();
        List<FuncModule> modules = funcModuleMapper.selectByExample(null);
        List<Func> funcs = funcMapper.selectByExample(null);

        List<Long> funcIds = roleMapper.selectFuncSetByRoleId(roleId);
        // 标记哪些功能点是该角色拥有的
        boolean[] selected = new boolean[funcs.size()];
        if(funcIds != null){
            for (int i=0 ; i<funcs.size() ; ++i){
                if(funcIds.contains(funcs.get(i).getId())){
                    selected[i] = true;
                }
            }
        }

        // 构造菜单
        generateMenu(menu, modules, funcs, selected);


        return menu;
    }



    /**
     * 获取用户的功能菜单
     * @param user
     * @return
     */
    @Override
    public List<Node> getUserMenu(User user) {
        List<Node> menu = new LinkedList<>();

        List<FuncModule> modules = funcModuleMapper.selectAllFuncModuleByUserId(user.getId());
        List<Func> funcs = funcMapper.selectFuncByUserId(user.getId());

        generateMenu(menu, modules, funcs, null);

        // 特殊角色处理
        List<String> roleStrlist = user.getRoleStrlist();
        // 如果该角色是学校信息管理员
        if(roleStrlist.contains(Role.UnivInfoSUP)){
            addUnivInfoSUPMenu(menu);
        }
        // 如果该角色是运维者
        if(roleStrlist.contains(Role.Operator)){
            addOperatorMenu(menu);
        }

        return menu;
    }


    /**
     * 运维者菜单
     * @param menu
     */
    public void addOperatorMenu(List<Node> menu){
        Node operatorNode = new Node("univManage", "系统管理", true, new LinkedList<>(), "OM1");
        {
            // 管理学校信息管理员
            operatorNode.getChildren().add(new Node("managerSetting", "管理员设置", false, null, "OF1_1"));
        }

        menu.add(operatorNode);
    }

    /**
     * 学校信息管理者菜单
     * @param menu
     */
    public void addUnivInfoSUPMenu(List<Node> menu){
        Node UnivInfoSUPNode = new Node("permissionSetting", "权限设置", true, new LinkedList<>(), "OM2");
        {
            UnivInfoSUPNode.getChildren().add(new Node("roleManage", "角色管理", false, null, "OF2_1"));
            UnivInfoSUPNode.getChildren().add(new Node("userPermission", "用户授权", false, null, "OF2_2"));
        }

        menu.add(UnivInfoSUPNode);
    }


    public void generateMenu(List<Node> menu, List<FuncModule> modules, List<Func> funcs, boolean[] selected){
        Map<Long, Node> modelId_node_Map = new HashMap<>();
        List<Node> nodes = new LinkedList<>();
        // 新增模块菜单
        for (int i=0 ; i<modules.size() ; i++){
            FuncModule module = modules.get(i);

            Node node = new Node();
            node.setTitle(module.getTitle());
            node.setParent(true);
            node.setNo("" + module.getId());
            node.setChildren(new LinkedList<>());

            nodes.add(node);

            modelId_node_Map.put(module.getId(), node);
        }

        //为模块菜单添加功能点
        for (int i=0 ; i<funcs.size() ; ++i){
            Func func = funcs.get(i);

            Node node = new Node();
            node.setTitle(func.getTitle());
            node.setKey(func.getRouteKey());
            node.setParent(false);
            node.setNo("" + func.getId());
            if(selected != null){
                node.setSelected(selected[i]);
            }

            // 将功能点归属于模块，如果这个功能点不属于某个模块，那么就让其属于“其他”模块
            Long funcModuleId = func.getModuleId();
            if(funcModuleId == null){
//                Node otherNode = modelId_node_Map.get(-1L);
//                if(otherNode == null){
//                    otherNode = new Node("other", "其他", true, new LinkedList<>(), "-1");
//                    nodes.add(otherNode);
//                    modelId_node_Map.put(-1L, otherNode);
//                }

//                otherNode.getChildren().add(node);
                nodes.add(node);
            }else {
                Node modelNode = modelId_node_Map.get(funcModuleId);
                modelNode.getChildren().add(node);
            }
        }

        for(int i=0 ; i<nodes.size() ; i++){
            if(!nodes.get(i).isParent() || nodes.get(i).getChildren().size() > 0){
                menu.add(nodes.get(i));
            }
        }
    }
}
