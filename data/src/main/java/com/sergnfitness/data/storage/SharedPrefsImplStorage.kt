package com.sergnfitness.cleanarchitect.data.storage

import android.content.Context
import com.sergnfitness.data.storage.storageModel.UserStorage

private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_USER_ID = "USER_ID"
private const val KEY_USER_FULLNAME = "USER_FULLNAME"
private const val KEY_USER_EMAIL = "USER_EMAIL"
private const val KEY_USER_PASSWORD = "USER_PASSWORD"
private const val KEY_USER_FITNESS_ID = "USER_FITNESS_ID"

private const val DEFAULT_KEY_USER_ID = "85000"
private const val DEFAULT_KEY_USER_FULLNAME = "TestUser"
private const val DEFAULT_KEY_USER_EMAIL = "TestUser"
private const val DEFAULT_KEY_USER_PASSWORD = "TestUser"
private const val DEFAULT_KEY_USER_FITNESS_ID = "85003"
private const val KEY_LAST_NAME = "Repository Impl"
private const val DEFAULT_LAST_NAME = "Default last name"
private const val DEFAULT_FIRST_NAME = "Default first name"


class SharedPrefsImplStorage(context: Context): SharedPrefsInterfaceStorage {

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

//    override fun save(userModelStorage: UserModelStorage): Boolean {
//        TODO("Not yet implemented")
//    }
//
//    override fun getUserModelStor(): UserModelStorage {
//        TODO("Not yet implemented")
//    }
//
//    override fun getIdUser(): Int {
//        TODO("Not yet implemented")
//    }

    override fun saveUser(user: UserStorage): Boolean {
        user.id?.let { sharedPreferences.edit().putInt(KEY_USER_ID, it).apply() }
        sharedPreferences.edit().putString(KEY_USER_FULLNAME,user.fullName.toString()).apply()
        sharedPreferences.edit().putString(KEY_USER_EMAIL,user.email.toString()).apply()
        sharedPreferences.edit().putString(KEY_USER_PASSWORD,user.password.toString()).apply()
        user.fitness_id?.let { sharedPreferences.edit().putInt(KEY_USER_FITNESS_ID, it).apply() }
        return true
    }
    override fun getUser(): UserStorage {
        return UserStorage(
        id = sharedPreferences.getInt(KEY_USER_ID, DEFAULT_KEY_USER_ID.toInt()) ?: DEFAULT_KEY_USER_ID.toInt(),
        fullName = sharedPreferences.getString(KEY_USER_FULLNAME, DEFAULT_KEY_USER_FULLNAME) ?: DEFAULT_KEY_USER_FULLNAME,
        email = sharedPreferences.getString(KEY_USER_EMAIL, DEFAULT_KEY_USER_EMAIL) ?: DEFAULT_KEY_USER_EMAIL,
        password = sharedPreferences.getString(KEY_USER_PASSWORD, DEFAULT_KEY_USER_PASSWORD) ?: DEFAULT_KEY_USER_PASSWORD,
        fitness_id = sharedPreferences.getInt(KEY_USER_FITNESS_ID, DEFAULT_KEY_USER_FITNESS_ID.toInt()) ?: DEFAULT_KEY_USER_FITNESS_ID.toInt(),

        )
    }


}