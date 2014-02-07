/*
 * Gerald Schreiber
 * <schreibergerald1@gmail.com>
 */

package at.big_buum_man.server;

import java.net.InetAddress;

// TODO new name for interface
public interface ServerMethods {

	// TODO switch from string to JSON object
	public void processMessage(InetAddress client, String message);

	public void registerNewClient(InetAddress client);

	// TODO define other methods called by clients
}
