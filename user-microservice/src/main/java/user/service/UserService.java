package user.service;

import user.bean.User;
import user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    /*
     * 登录
     */
    public User login(String username, String password) {
        List<User> list = null;
        list = userRepository.findByUsername(username);
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return null;
        }
        // 取用户信息
        User user = list.get(0);
        // 判断密码是否正确
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            // 2、如果不正确，返回登录失败
            return null;
        }
        return user;
    }

    /*
     * 注册
     */
    public User register(String username, String password, String email) {
        // 判断系统中是否有相同的username
        List<User> list = null;
        list = userRepository.findByUsername(username);
        if (list != null && list.size() != 0) {
            return null;
        }
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setRegistertime(new Date());
        user.setLastlogintime(new Date());
        // 对密码进行md5加密
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Pass);
        return userRepository.save(user);
    }

    /*
     * 修改密码
     */
    public Boolean resetPw(int userid, String pw) {
        List<User> list = null;
        list = userRepository.findByUserid(userid);
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return false;
        }
        // 取用户信息
        User user = list.get(0);

        String md5Pass = DigestUtils.md5DigestAsHex(pw.getBytes());
        userRepository.modifyPasswordByUserid(md5Pass, user.getUserid());
        return true;
    }

    /**
     * 根据 userid 获取 username
     */
    public String getUsername(int userid) {
        List<User> list = null;
        list = userRepository.findByUserid(userid);
        if (list == null || list.size() == 0) {
            // 返回登录失败
            return "";
        }
        // 取用户信息
        User user = list.get(0);
        return user.getUsername();
    }
}
