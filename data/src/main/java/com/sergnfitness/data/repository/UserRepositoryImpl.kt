package com.sergnfitness.cleanarchitect.data.repository


import android.util.Log
import com.sergnfitness.cleanarchitect.data.storage.SharedPrefsInterfaceStorage
import com.sergnfitness.data.storage.storageModel.UserStorage
import com.sergnfitness.domain.models.user.DataUser
import com.sergnfitness.domain.models.user.User
import com.sergnfitness.domain.repository.UserRepository
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val sharedPrefsInterfaceStorage: SharedPrefsInterfaceStorage,
//    appContext: Application
) :
    UserRepository {

    init {
        val appName =
            "testttttt********"//appContext.getString(androidx.core.R.string.status_bar_notification_info_overflow)
        Log.e("UserRepositoryImpl", "Heloo from ApiRepositoryImpl $appName")
    }

//    override fun saveName(saveparam: SaveUserNameParam): Boolean {
//        val user: UserModelStorage = mapToStorage(saveparam)
//        val result = sharedPresInterfaceStorage.save(user)
//        return result
//    }

    override fun saveUser(saveParam: User): Boolean {
        val user: UserStorage = mapUserToStorage(saveParam)
        val result = sharedPrefsInterfaceStorage.saveUser(user)
        return result
    }

    override fun getUser(): User {
//        val user: User = mapUserToDomain(getParam)
        val user = sharedPrefsInterfaceStorage.getUser()
        return mapUserToDomain(getParam = user)
    }

    override fun saveDataUser(user: User): Boolean {
        val userStorage: UserStorage = mapUserToStorage(user)
        val result = sharedPrefsInterfaceStorage.saveUserClass(userStorage)
        return result
    }

    override fun getDataUser(): MutableList<String> {

        return sharedPrefsInterfaceStorage.getUserClass()
    }

    override fun createExemplarClassDataUserStorageUseRepos(list: MutableList<String>): DataUser {
        var us = DataUser()
        var count = 0
        DataUser::class.java.declaredFields.forEach() { member ->
            var type = member.name.javaClass.name
            when (type) {
                "java.lang.String" -> {
                    list.add(count, "")
                    us.javaClass.getDeclaredField(member.name).let { list.get(count) ?: "" }
                }
                "java.lang.Boolean" -> {
                    list.add(count, false.toString())
                    us.javaClass.getDeclaredField(member.name)
                        .let { list.get(count).toBoolean() ?: false }
                }
                "java.lang.Int" -> {
                    list.add(count, "0")
                    us.javaClass.getDeclaredField(member.name).let { list.get(count).toInt() ?: 0 }
                }
                else -> {
                    list.add(count, "")
                    us.javaClass.getDeclaredField(member.name).let { "" }
                }
            }
            count = count + 1
        }
        return us
    }

    override fun createExemplarClassUserUseRepos(list: MutableList<String>): User {
        var us = User()
        var count = 0
        User::class.java.declaredFields.forEach() { member ->
            println("forEach  ${member.name}")
            var type = member.type.name  //.toString()
            var n: Any
            println(type)
            when (type) {
                "java.lang.String" -> {
                    println(member.name)
                    list.add(count, "em")
                    println("list ${list.toString()}")
                    n = us.javaClass.getDeclaredField(member.name)
                    n.isAccessible = true
                    n.set(us, (list.get(count)))
                }
                "java.lang.Boolean" -> {
                    println(member.name)
                    list.add(count, false.toString())
                    println("list ${list.toString()}")
                    n = us.javaClass.getDeclaredField(member.name)
                    n.isAccessible = true
                    n.setBoolean(us, list.get(count).toBoolean() ?: false)
                }
                "java.lang.Integer" -> {
                    println(member.name)
                    list.add(count, "0")
                    println("list ${list.toString()}")
                    n = us.javaClass.getDeclaredField(member.name)
                    n.isAccessible = true
                    n.set(us, list.get(count).toInt() ?: 0)
                }
                else -> {
                    println(member.name)
                    list.add(count, "")
                    println("list ${list.toString()}")
                    us.javaClass.getDeclaredField(member.name).set(us, "")
                }
            }
            count = count + 1
        }
        println(us)
        return us
    }

//    override fun getUserSharedPref(): User {
//        val user = sharedPresInterfaceStorage.getUserModelStor()
//        return mapToDomain(user)
//
//    }
//
//    override fun getId(): Int {
//        val idUser: Int = sharedPresInterfaceStorage.getIdUser()
//        return idUser
//    }

    private fun mapUserToStorage(saveParam: User): UserStorage {
        return UserStorage(
            id = saveParam.id,
            fullName = saveParam.fullName,
            email = saveParam.email,
            password = saveParam.password,
            fitness_id = saveParam.fitness_id,
        )
    }

    private fun mapUserToDomain(getParam: UserStorage): User {
        return User(
            id = getParam.id,
            fullName = getParam.fullName,
            email = getParam.email,
            password = getParam.password,
            fitness_id = getParam.fitness_id,
        )
    }

//    private fun mapToStorage(saveparam: SaveUserNameParam): UserModelStorage {
//        return UserModelStorage(
//            id = saveparam.idUser,
////            fullName = saveparam.fullName,
//            email = saveparam.email,
////            password = saveparam.password,
////            fitness_id = saveparam.fitness_id,
//        )
//    }
//
//    private fun mapToDomain(userModelStorage: UserModelStorage): User {
//        return User(
//            id = userModelStorage.id,
//            fullName = userModelStorage.fullName,
//            email = userModelStorage.email,
//            password = userModelStorage.password,
//            fitness_id = userModelStorage.fitness_id,
//        )
//    }


}