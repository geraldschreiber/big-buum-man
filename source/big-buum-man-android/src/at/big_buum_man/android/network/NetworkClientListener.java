/*
 * Gerald Schreiber
 * <schreibergerald1@gmail.com>
 */

package at.big_buum_man.android.network;

import java.net.InetAddress;

public interface NetworkClientListener {

	public void processFoundServer(InetAddress address);
}
