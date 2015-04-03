package zx.soft.api.adduser.gplus;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.PeopleFeed;
import com.google.api.services.plus.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zx.soft.api.adduser.common.MybatisConfig;
import zx.soft.api.adduser.dao.server.MonitorUserDaoServer;
import zx.soft.api.domain.GplusApp;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/**
 * Created by jimbo on 15-3-31.
 */
public class MonitorUserGplus {

    private static final Logger logger = LoggerFactory.getLogger(MonitorUserGplus.class);

    private static FileDataStoreFactory dataStoreFactory;
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private MonitorUserDaoServer server = new MonitorUserDaoServer(MybatisConfig.Servers.GBXM);
    protected GoogleAuthorizationCodeFlow flow;
    private static Plus plus;

    public List<Person> createFriendship(String name) {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //数据库获取Gplus Apps基本信息
        List<GplusApp> apps = getGplusApps();
        GplusApp app = apps.get(apps.size()-1);
        String appName = app.getAppName();
        String clientId = app.getClientId();
        String clientSecret = app.getClientSecret();
        //认证信息存储地址
        File credentialsDir = new File(System.getProperty("user.home"),".gplus/credentials_"+appName);

        //根据用户名来获取用户基本数据
        //返回值为用户基本信息列表
        try {
            dataStoreFactory = new FileDataStoreFactory(credentialsDir);
            flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,JSON_FACTORY,clientId,clientSecret,
                    Collections.singleton(PlusScopes.PLUS_ME)).setDataStoreFactory(dataStoreFactory).build();
            Credential credential = flow.loadCredential("user");
            credential.refreshToken();
            credential.getAccessToken();
            plus = new Plus.Builder(httpTransport,JSON_FACTORY,credential).setApplicationName(appName).build();
            Plus.People.Search peopleSearch = plus.people().search(name);
            peopleSearch.setMaxResults(50L);
            PeopleFeed feed = peopleSearch.execute();
            List<Person> people = feed.getItems();

            return people;
        } catch (IOException e) {
            logger.error("获取Gplus用基本信息并将用户出错");
            throw new RuntimeException(e);
        }
    }

    public List<GplusApp> getGplusApps(){
        return server.getGplusApps();
    }



}
