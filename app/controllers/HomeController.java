package controllers;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import play.*;
import play.mvc.*;

import services.CassandraClient;
import views.html.*;

import java.util.Optional;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        final CassandraClient client = new CassandraClient();
        final String ipAddress = "localhost";
        final int port = 9042;
        client.connect(ipAddress,port);

        final ResultSet userResults = client.getSession().execute("SELECT * FROM garrettkeyspace.users");

        final Row userRow = userResults.one();

        client.close();

        return ok(index.render(userRow.toString()));
    }

}
