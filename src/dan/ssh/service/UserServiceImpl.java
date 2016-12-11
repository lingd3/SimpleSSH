package dan.ssh.service;

import java.util.List;

import dan.ssh.dao.UserDao;
import dan.ssh.model.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean checkUser(User user) {
		if (userDao.checkUser(user)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {
		if (userDao.addUser(user)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(int id) {
		if (userDao.deleteUser(id)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		if (userDao.updateUser(user)) {
			return true;
		}
		return false;
	}

	@Override
	public List<User> queryUser(User user) {
		List<User> users = userDao.queryUser(user);
		return users;
	}

	@Override
	public User queryById(int id) {
		User u = userDao.queryById(id);
		return u;
	}

	@Override
	public List<User> queryAll() {
		List<User> users = userDao.queryAll();
		return users;
	}

}
