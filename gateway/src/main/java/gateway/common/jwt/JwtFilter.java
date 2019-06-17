package gateway.common.jwt;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JwtFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    public String filterType() {
        return "前置过滤";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // HttpServletResponse response = ctx.getResponse();

        //等到请求头信息authorization信息
        final String accessToken = request.getHeader("authorization");
        log.info(String.format("print out access token"));
        log.info(String.format(accessToken));
        if ("OPTIONS".equals(request.getMethod())) {
            //response.setStatus(HttpServletResponse.SC_OK);
        } else {
            Claims claims;
            if (null == accessToken) {
                claims = null;
            } else {
                // 从Redis 中查看 token 是否过期
                Audience audience = new Audience();
                claims = JwtHelper.parseJWT(accessToken, audience.getBase64Secret());
                log.info(String.format("print out access token not null"));
                if (claims != null) {
                    // throw new CommonException(401, "token 错误");
                    String userid = claims.getId();
                    log.info(String.format("print out userid token"));
                    log.info(String.format(userid));
                    request.setAttribute("userid", Integer.parseInt(userid));
                }
            }
            request.setAttribute("claims", claims);
        }

        return null;
    }
}