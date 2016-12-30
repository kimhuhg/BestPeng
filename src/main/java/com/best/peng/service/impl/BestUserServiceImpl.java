package com.best.peng.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.best.peng.domian.BestFolder;
import com.best.peng.domian.BestRootFolder;
import com.best.peng.domian.BestUser;
import com.best.peng.global.BestConstant;
import com.best.peng.repository.BestUserRepository;
import com.best.peng.service.BestFolderService;
import com.best.peng.service.BestRootFolderService;
import com.best.peng.service.BestUserService;
import com.best.peng.util.MD5Utils;

@Service
public class BestUserServiceImpl implements BestUserService {
	
	@Autowired
	private BestUserRepository bestUserRepository;
	
	@Autowired
	private BestRootFolderService bestRootFolderService;
	
	@Autowired
	private BestFolderService bestFolderService;

	/**
	 * 添加或修改用户
	 */
	@Override
	@Transactional//开启事务
	public BestUser addOrUpdate(BestUser user) {
		
		boolean flag=user.getUserId()==null?true:false;
		//密码加密
		String password=MD5Utils.encrypt(user.getPassword()+BestConstant.PWD_TOKEN, MD5Utils.MD5_KEY);
		
		Date nowTime=Calendar.getInstance().getTime();
		user.setUpdateDate(nowTime);
		
		if(flag){
			user.setCreateDate(nowTime);
			user.setLoginDate(nowTime);
			user.setPassword(password);
			user.setStatus(0);
			user.setValid(true);
			user.setUserName(user.getEmail());
			user.setAvatarUrl(BestConstant.AVATAR_URL);
		}
		BestUser bestUser=bestUserRepository.save(user);
		
		//为新用户时
		if(flag){
			//创建用户根文件夹
			BestRootFolder rootFolder=new BestRootFolder();
			rootFolder.setUserId(bestUser.getUserId());
			BestRootFolder rootFolderDb = bestRootFolderService.addOrUpdate(rootFolder);
			
			//创建默认文件夹
			for (String type : BestConstant.FOLDER_TYPE) {
				BestFolder bestFolder=new BestFolder();
				bestFolder.setName(type);
				bestFolder.setParentId(rootFolderDb.getRootFolderId());
				bestFolder.setRootFoldeId(rootFolderDb.getRootFolderId());
				bestFolder.setUpdateDate(nowTime);
				bestFolder.setValid(true);
				bestFolderService.addOrUpdate(bestFolder);
			}
			
		}
		
		return bestUser;
	}

	/**
	 * 根据userId获取用户
	 */
	@Override
	public BestUser getUserById(Long userId) {
		return bestUserRepository.findOne(userId);
	}

	/**
	 * 删除用户
	 */
	@Override
	public void delete(Long userId) {
		bestUserRepository.delete(userId);
	}

	/**
	 * 根据Email获取用户
	 */
	@Override
	public BestUser findBestUserByEmail(String email) {
		return bestUserRepository.findByEmail(email);
	}

	/**
	 * 根据Email和密码获取用户
	 */
	@Override
	public BestUser findByBestUserByEmailAndPwd(String email, String password) {
		password=MD5Utils.encrypt(password+BestConstant.PWD_TOKEN, MD5Utils.MD5_KEY);
		return bestUserRepository.findByEmailAndPassword(email, password);
	}

	/**
	 * 更新用户登录时间
	 */
	@Override
	@Transactional //更新，必须开启事务
	public int updateBestUserLoginDate(String email) {
		
		return bestUserRepository.updateBestUserLoginDate(Calendar.getInstance().getTime(), email);
	}

	/**
	 * 复杂查询
	 */
	@Override
	public Page<BestUser> list(Pageable pageable) {	
		//通常使用 Specification 的匿名内部类
		Specification<BestUser> specification = new Specification<BestUser>() {
			/**
			 * @param *root: 代表查询的实体类. 
			 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象. 
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件. 
			 */
			@Override
			public Predicate toPredicate(Root<BestUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("user_id");
				
				Predicate predicate = cb.gt(path, 1);
				return predicate;
			}
		};
		
		Page<BestUser> page = bestUserRepository.findAll(specification,pageable);
		
		return page;
	}

	/**
	 * 修改密码
	 */
	@Transactional
	@Override
	public int updateBestUserPassword(Long userId, String oldPassword,String newPassword) {
		BestUser user=getUserById(userId);
		oldPassword=MD5Utils.encrypt(oldPassword+BestConstant.PWD_TOKEN, MD5Utils.MD5_KEY);
		int count=0;
		if(user.getPassword().equals(oldPassword)){
			newPassword=MD5Utils.encrypt(newPassword+BestConstant.PWD_TOKEN, MD5Utils.MD5_KEY);
			count=bestUserRepository.updateBestUserPassword(userId, newPassword);
		}
		
		return count;
	}

	
	
}
