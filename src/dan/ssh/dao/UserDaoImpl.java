package dan.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dan.ssh.model.User;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//用以登录时检查数据库中是否存在该用户
	@Override
	public boolean checkUser(User user) {

		//开启session
		Session session = sessionFactory.openSession();
		//开启事务
		session.beginTransaction();
		//查询语句
		Query query = session.createQuery(" from User u where u.id=:id and u.name=:name");
		//设定查询语句中变量的值
		query.setParameter("id", user.getId());
		query.setParameter("name", user.getName());
		//查询结果
		User u = (User)query.uniqueResult();
		//事务提交并关闭
		session.getTransaction().commit();
		session.close();
		
		if (u == null) {
			return true;
		}
		return false;
	}

	//添加用户
	@Override
	public boolean addUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	//删除用户
	@Override
	public boolean deleteUser(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//通过id找到user!
		User u = (User)session.get(User.class, id);
		session.delete(u);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	//修改用户信息
	@Override
	public boolean updateUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(user);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	//查询用户
	//根据name或company查询
	@Override
	public List<User> queryUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//使用StringBuffer便于后面根据不同条件连接查询语句
		StringBuffer hq = new StringBuffer(" from User u where 1=1");
		
		//参数的值得集合
		ArrayList<String> params = new ArrayList<String>();
		if (user.getName() != null && !user.getName().equals("")) {
			hq.append(" and u.name=?");
			params.add(user.getName());
		}
		if (user.getCompany() != null && !user.getCompany().equals("")) {
			hq.append(" and u.company=?");
			params.add(user.getCompany());
		}
		
		//根据参数的数量，设定查询条件的个数
		Query query = session.createQuery(hq.toString());
		for (int i = 0; i < params.size(); i++) {
			query.setString(i, params.get(i));
		}
		//查询结果
		List<User> users = query.list();
		session.getTransaction().commit();
		session.close();
		return users;
	}

	@Override
	public User queryById(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User u = (User)session.get(User.class, id);
		session.getTransaction().commit();
		session.close();
		return u;
	}

	//查询所有用户
	@Override
	public List<User> queryAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery(" from User u");
		List<User> users = query.list();
		session.getTransaction().commit();
		session.close();
		return users;
	}

}
