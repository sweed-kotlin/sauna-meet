package com.sweed.saunameet

import android.content.Context


class ResourcesProvider private constructor(context: Context) {
    private val mContext: Context

    init {
        mContext = context;
    }
    fun getString(stringId: Int ): String{
        return mContext.getString(stringId);
    }

    companion object : SingletonHolder<ResourcesProvider, Context>(::ResourcesProvider)
}

//  lazy initialization
open class SingletonHolder<out T: Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}