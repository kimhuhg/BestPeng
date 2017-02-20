package com.best.peng.sys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树
 * @author zhoupeng
 *
 */
public class MenuTree {
private StringBuffer html = new StringBuffer();
	
    private List<Menu> menus;
    
    public MenuTree(List<Menu> menus){
        this.menus = menus;
    }
    
	public String buildTree(){  
        html.append("<ul class='nav nav-list'>");
        html.append("<li class='active'><a href='/'><i class='menu-icon fa fa-tachometer'></i>");
        html.append("<span class='menu-text'> 控制台 </span></a><b class='arrow'></b></li>");
        for (Menu node : menus) {  
            Integer id = node.getId();
            
            if (node.getParentId() == 0) {
            	
            	List<Menu> children = getChildren(node);
            	//如果有下级目录
                if (!children.isEmpty()) {
                	html.append("<li class=''><a href='#' class='dropdown-toggle'><i class='menu-icon fa "+node.getIconName()+"'></i>");
                	html.append("<span class='menu-text'>"+node.getMenuName()+"</span><b class='arrow fa fa-angle-down'></b></a><b class='arrow'></b>");
                }else{
                	html.append("<li class=''><a href='");
                	html.append(node.getMenuLink()+"'><i class='menu-icon fa "+node.getIconName()+"'></i>");
                	html.append("<span class='menu-text'>"+node.getMenuName()+"</span><b class='arrow'></b>");
                	html.append("</a><b class='arrow'></b>");
                }
//                html.append("<li id='" + id + "'>" + node.getMenuName()+ "</li>");  
                build(node);
                html.append("</li>");
            }
        }  
        html.append("</ul>");  
        return html.toString();  
    }  
      
    private void build(Menu node){  
        List<Menu> children = getChildren(node);  
        if (!children.isEmpty()) {
        	html.append("<ul class='submenu'>");
            
            for (Menu child : children) {
                Integer id = child.getId();
                List<Menu> children2 = getChildren(child);  
                if (!children2.isEmpty()) {
                	html.append("<li class=''><a href='#' class='dropdown-toggle'><i class='menu-icon fa "+child.getIconName()+"'></i>");
                	html.append("<span class='menu-text'>"+child.getMenuName()+"</span><b class='arrow fa fa-angle-down'></b></a><b class='arrow'></b>");
                }else{
                	html.append("<li class=''><a href='");
                	html.append(child.getMenuLink()+"'><i class='menu-icon fa "+child.getIconName()+"'></i>");
                	html.append("<span class='menu-text'>"+child.getMenuName()+"</span><b class='arrow'></b>");
                	html.append("</a><b class='arrow'></b>");
                }
//              html.append("<li id='" + id + "'>" + child.getMenuName()+ "</li>");
                build(child);
                html.append("</li>");
            }  
            html.append("</ul>");  
        }   
    }  
      
    private List<Menu> getChildren(Menu node){  
        List<Menu> children = new ArrayList<Menu>();  
        Integer id = node.getId();  
        for (Menu child : menus) {  
            if (id.equals(child.getParentId())) {  
                children.add(child);  
            }  
        }  
        return children;  
    }
}
