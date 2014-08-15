package com.bjsxt.album.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.bjsxt.album.service.AlbumService;
import com.opensymphony.xwork2.ActionSupport;

public class AlbumAction extends ActionSupport {
	private AlbumService albumService  = new AlbumService();
	public String index(){
		System.out.println("start find Album");
		List list = albumService.findAlbum();
		ServletActionContext.getRequest().setAttribute("album",list);
		return SUCCESS;
	}
	
}
