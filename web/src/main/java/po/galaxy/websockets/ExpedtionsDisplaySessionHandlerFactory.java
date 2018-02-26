/*
 * File : ExpeditionsDisplaySessionHandlerFactory.java
 * Description : Factory pattern returning SessionHandler
 *
 * Author : Popov Denys
 * Created : 25 Feb, 2018
 *
 * Modified : { date: 25/02/18
 *             ,time: 06:50 PM }
 * Modified by: Popov Denys
 *
 * Last modification : applying factory pattern to get Session instance
 */

package po.galaxy.websockets;

public class ExpedtionsDisplaySessionHandlerFactory {

    private static ExpeditionsDisplaySessionHandler handler;

    public static ExpeditionsDisplaySessionHandler getHandler() {
        if(handler == null) handler = new ExpeditionsDisplaySessionHandler();
        return handler;
    }
}
