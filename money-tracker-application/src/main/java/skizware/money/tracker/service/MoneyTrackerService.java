package skizware.money.tracker.service;

import com.skizware.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: david.anderson
 * Date: 2015/08/23
 * Time: 9:10 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MoneyTrackerService {

    public User getUserAccount(final String emailAddress);

    public User enrollUser(final String emailAddress);

}
