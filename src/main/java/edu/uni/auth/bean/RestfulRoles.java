package edu.uni.auth.bean;

import java.util.List;

/**
 * @author 何亮
 */
public class RestfulRoles {
    private List<String> PostRoles;
    private List<String> DeleteRoles;
    private List<String> PutRoles;
    private List<String> GetRoles;

    public List<String> getPostRoles() {
        return PostRoles;
    }

    public void setPostRoles(List<String> postRoles) {
        PostRoles = postRoles;
    }

    public List<String> getDeleteRoles() {
        return DeleteRoles;
    }

    public void setDeleteRoles(List<String> deleteRoles) {
        DeleteRoles = deleteRoles;
    }

    public List<String> getPutRoles() {
        return PutRoles;
    }

    public void setPutRoles(List<String> putRoles) {
        PutRoles = putRoles;
    }

    public List<String> getGetRoles() {
        return GetRoles;
    }

    public void setGetRoles(List<String> getRoles) {
        GetRoles = getRoles;
    }

    public String generateRestfulPermission(){
        String restfulPermission = "restfulFilter[";
        Boolean flag = false;
        restfulPermission += generate("POST", flag, PostRoles);
        if(restfulPermission.contains("POST"))
            flag=true;
        restfulPermission += generate("DELETE", flag, DeleteRoles);
        if(restfulPermission.contains("DELETE"))
            flag=true;
        restfulPermission += generate("PUT", flag, PutRoles);
        if(restfulPermission.contains("PUT"))
            flag=true;
        restfulPermission += generate("GET", flag, GetRoles);
        restfulPermission += "]";
        return restfulPermission;
    }

    private String generate(String method, Boolean flag, List<String> roles){
        String str = "";
        if(roles != null && roles.size() > 0){
            if(flag)
                str += ",";
            str += method +"(";
            for(int i=0 ; i<roles.size() ; ++i){
                if(i > 0)
                    str +="/";
                str += roles.get(i);
            }
            str += ")";
        }
        return str;
    }
}
