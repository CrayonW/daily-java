import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.Joke;
import entity.JokeRsp;
import entity.Result;
import util.JdbcUtil;
import util.WebUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class startClass {
  private static final String url = "http://v.juhe.cn/joke/content/list.php";

  public static void main(String[] args) {
    Connection conn = JdbcUtil.getConnection();
    Long time = System.currentTimeMillis();
    String timestap = time.toString().substring(0, 10);
    long startTime=System.currentTimeMillis();   //获取开始时间
    getJokeJSON(timestap);  //测试的代码段
    long endTime=System.currentTimeMillis(); //获取结束时间
    System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
  }

  private static void getJokeJSON(String timestap) {
    Boolean flag = true;
	Integer page = 1;
    while(flag) {
      Map<String, String> map = new HashMap<>();
      map.put("key", "88f1c3114d0b668d153eaac326d013f3");
      map.put("page", page.toString());
      map.put("pageSize", "20");
      map.put("sort", "desc");
      map.put("time", timestap);
      try {
        String rsp = WebUtil.doGet(url, map, "UTF-8");
        JokeRsp jokeRsp = JSON.parseObject(rsp, JokeRsp.class);
        if (jokeRsp == null) {
          flag = false;
        } else {
          Result result = jokeRsp.getResult();
          if (result == null) {
            flag = false;
          } else {
            List<Joke> jokes = jokeRsp.getResult().getData();
            System.out.println(JSONObject.toJSONString(jokes));
            if (jokes == null || jokes.size() <= 0) {
              flag = false;
            } else {
              //插入到数据库
              insertData(jokes);
            }
            page++;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void insertData(List<Joke> jokes) {
    try {
      Connection conn = JdbcUtil.getConnection();
      String sql = "insert into joke (content, unixtime, hashId, updateTime) value (?, ?, ?, ?)";
      PreparedStatement prest = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
              ResultSet.CONCUR_READ_ONLY);
      conn.setAutoCommit(false);
      for (Joke joke : jokes) {
        prest.setString(1, joke.getContent());
        prest.setString(2, joke.getUnixtime());
        prest.setString(3, joke.getHashId());
        prest.setString(4, joke.getUpdatetime());
        prest.addBatch();
      }
      prest.executeBatch();
      conn.commit();
      JdbcUtil.close(conn, prest);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}