package user.controller;

import user.service.UserService;
import user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import user.common.exception.*;
import user.common.jwt.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.login(username, password);
        if(user == null) {
            throw new CommonException(300100, "用户名不存在或密码不正确");
        }
        Audience audience = new Audience();
        String jwtToken = JwtHelper.createJWT(user.getUsername(),
                user.getUserid(),
                audience.getClientId(),
                audience.getName(),
                audience.getExpiresSecond()*1000,
                audience.getBase64Secret());
        Map<String , Object> info = new HashMap<String , Object>();
        info.put("authorization", jwtToken);
        info.put("user", user);
        return handleResponseData(0, info);
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email){
        User user = userService.register(username, password, email);
        if (user == null) {
            throw new CommonException(300101, "该username已存在, 请换一个昵称");
        }
        return handleResponseData(0, user);
    }

    /**
     *  修改密码
     *  @return
     */
    @PostMapping("/resetPw")
    public Map<String, Object> resetPw(final ServletRequest req, @RequestParam("password") String password){
        final HttpServletRequest request = (HttpServletRequest) req;
        Object claims = req.getAttribute("claims");
        if (claims == null) {
            throw new CommonException(401, "用户token验证失败，请重新登录");
        }
        Object userid = req.getAttribute("userid");
        Boolean result = userService.resetPw(Integer.parseInt(String.valueOf(userid)), password);
        if (!result) {
            throw new CommonException(300103, "用户不存在，请重新登录");
        }
        return handleResponseData(0, result);
    }

    /**
     * 获取用户昵称信息
     */
    @PostMapping("/getUsername")
    public Map<String, Object> getUsername(@RequestParam("userid")int userid) {
        String username = userService.getUsername(userid);
        return handleResponseData(0, username);
    }
}
