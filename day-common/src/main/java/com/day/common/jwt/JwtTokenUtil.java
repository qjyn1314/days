package com.day.common.jwt;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.*;
import java.lang.reflect.Field;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * explain: jwt工具类
 * </p>
 *
 * @author wangjunming
 * @since 2020/3/23 12:26
 */
@Slf4j
public final class JwtTokenUtil {
    /**
     * jwt密钥
     */
    private final String secret = "DAYS_20200827145930";

    /**
     * jwt过期时间-默认是24小时
     */
    private final int expireTime =  24;

    /**
     * 初始化jwt密钥和过期时间
     *
     * --主要用于在用户登录过后的信息存储，需要将生成的token返回给前端，并在每次请求的时候，在请求头添加此数据信息
     *
     * @author wangjunming
     * @since 2020/3/23 13:11
     */
    public JwtTokenUtil() {
    }

    /**
     * Map转实体类
     *
     * @param map    需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
     * @param entityClass 需要转化成的实体类
     * @author wangjunming
     * @since 2020/3/23 14:18
     */
    private static <T> T mapToEntity(Map<String, Object> map, Class<T> entityClass) {
        T t = null;
        try {
            t = entityClass.newInstance();
            for (Field field : entityClass.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException e) {
            log.error("解析map  到  entity  失败-InstantiationException", e);
        } catch (IllegalAccessException e) {
            log.error("解析map  到  entity  失败-IllegalAccessException", e);
        } catch (Exception e) {
            log.error("解析map  到  entity  失败-Exception", e);
        }
        return t;
    }

    /**
     * 生成登录用户信息的token
     *
     * @param userManager 登录用户信息
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public String doUserInfoToken(String userManager) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put("loginUser", userManager);
        return generateToken(claims, this.secret, this.expireTime);
    }

    /**
     * 生成任意token
     *
     * @param claims 需要存储的有效信息
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public String doGenerateToken(Map<String, Object> claims) {
        return generateToken(claims, this.secret, this.expireTime);
    }

    /**
     * 获取生成的任意token中存储的所有信息
     *
     * @param token 存储有效信息的token
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public Claims getGenerateToken(String token) {
        return getClaimFromToken(token);
    }

    /**
     * 封装生成token
     *
     * @author wangjunming
     * @since 2020/3/23 12:53
     */
    private String generateToken(Map<String, Object> claims, String subject, int expireSecs) {
        final Calendar nowCalendar = Calendar.getInstance();
        final Date createdDate = nowCalendar.getTime();
        nowCalendar.add(Calendar.HOUR, expireSecs);
        final Date expirationDate = nowCalendar.getTime();
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    /**
     * 获取jwt的payload部分
     *
     * @author wangjunming
     * @since 2020/3/23 13:16
     */
    private Claims getClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 解析token是否正确
     *
     * @author wangjunming
     * @since 2020/3/23 13:16
     */
    private boolean parseToken(String token) {
        boolean flag = false;
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
            flag = true;
        } catch (ExpiredJwtException e) {
            log.error("JWT-解析token异常-ExpiredJwtException：", e);
        } catch (UnsupportedJwtException e) {
            log.error("JWT-解析token异常-UnsupportedJwtException：", e);
        } catch (MalformedJwtException e) {
            log.error("JWT-解析token异常-MalformedJwtException：", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT-解析token异常-IllegalArgumentException：", e);
        }
        return flag;
    }

    /**
     * 获取登录用户信息,调用此接口之前请先调用parseToken(userToken)并保证返回true
     *
     * @param userToken 存储登录用户信息的token
     * @author wangjunming
     * @since 2020/3/23 12:54
     */
    public String getUserInfoByToken(String userToken) {
        return !StrUtil.hasBlank(userToken) ? mapToEntity(getClaimFromToken(userToken).get("loginUser", LinkedHashMap.class), String.class) : null;
    }


}
