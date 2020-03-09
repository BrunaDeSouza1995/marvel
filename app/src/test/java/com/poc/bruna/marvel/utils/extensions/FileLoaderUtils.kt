package com.poc.bruna.marvel.utils.extensions

import com.google.gson.Gson
import com.google.gson.internal.Primitives
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

object FileLoaderUtils {

    fun <T> getDataFromJsonFile(testClass: Class<*>?, clazz: Class<T>?, testPath: String?): T? {
        return try {
            val readerPath: String = FileLoaderUtils.getFile(testClass!!, testPath!!)!!.path
            val bufferedReader = BufferedReader(FileReader(readerPath))
            val `object`: T = Gson().fromJson(bufferedReader, clazz)
            Primitives.wrap(clazz).cast(`object`)
        } catch (e: Exception) {
            println(e.localizedMessage)
            null
        }
    }

    private fun getFile(
        testClass: Class<*>,
        testPath: String
    ): File? {
        val classLoader = testClass.classLoader
        val resource =
            classLoader?.getResource(testPath)
        return File(resource!!.path)
    }
}