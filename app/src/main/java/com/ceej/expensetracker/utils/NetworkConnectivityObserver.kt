package com.ceej.expensetracker.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.ceej.common.network.ConnectivityObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class NetworkConnectivityObserver(
    context: Context
) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
@SuppressLint("MissingPermissions")
    override fun observe(): Flow<ConnectivityObserver.Status> = callbackFlow {
        val callback = object : ConnectivityManager.NetworkCallback() {

<<<<<<< HEAD:data/src/main/java/com/ceej/common/expensetracker/data/network/NetworkConnectivityObserver.kt
            override fun onAvailable(network: Network) {
                trySend(ConnectivityObserver.Status.Available)
=======
    @SuppressLint("MissingPermission")
    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectivityObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(ConnectivityObserver.Status.Losing) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(ConnectivityObserver.Status.Lost) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(ConnectivityObserver.Status.Unavailable) }
                }
>>>>>>> origin/master:app/src/main/java/com/ceej/expensetracker/utils/NetworkConnectivityObserver.kt
            }

            override fun onLost(network: Network) {
                trySend(ConnectivityObserver.Status.Lost)
            }
<<<<<<< HEAD:data/src/main/java/com/ceej/common/expensetracker/data/network/NetworkConnectivityObserver.kt

            override fun onUnavailable() {
                trySend(ConnectivityObserver.Status.Unavailable)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(ConnectivityObserver.Status.Losing)
            }
        }

        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }.distinctUntilChanged()
}

=======
        }.distinctUntilChanged()
    }
}
>>>>>>> origin/master:app/src/main/java/com/ceej/expensetracker/utils/NetworkConnectivityObserver.kt
