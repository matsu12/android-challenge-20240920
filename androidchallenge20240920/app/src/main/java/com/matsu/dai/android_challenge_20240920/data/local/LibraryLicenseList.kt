package com.matsu.dai.android_challenge_20240920.data.local

import android.content.Context
import com.matsu.dai.android_challenge_20240920.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class LibraryLicenseList @Inject constructor(val context: Context) {

    suspend fun create(): List<LibraryLicense> {
        val licenses = loadLibraries(context).map {
            LibraryLicense(it.name, loadLicense(context, it))
        }
        return licenses
    }

    private suspend fun loadLibraries(context: Context): List<Library> {
        return withContext(Dispatchers.IO) {
            val inputSteam = context.resources.openRawResource(R.raw.third_party_license_metadata)
            inputSteam.use { inputSteam ->
                val reader = BufferedReader(InputStreamReader(inputSteam, "UTF-8"))
                reader.use { bufferedReader ->
                    val libraries = mutableListOf<Library>()
                    while (true) {
                        val line = bufferedReader.readLine() ?: break
                        val (position, name) = line.split(' ', limit = 2)
                        val (offset, length) = position.split(':').map { it.toInt() }
                        libraries.add(Library(name, offset, length))
                    }
                    libraries.toList()
                }
            }
        }
    }

    private suspend fun loadLicense(context: Context, library: Library): String {
        return withContext(Dispatchers.IO) {
            val charArray = CharArray(library.length)
            val inputStream = context.resources.openRawResource(R.raw.third_party_licenses)
            inputStream.use { stream ->
                val bufferedReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
                bufferedReader.use { reader ->
                    reader.skip(library.offset.toLong())
                    reader.read(charArray, 0, library.length)
                }
            }
            String(charArray)
        }
    }
}

data class Library(
    val name: String,
    val offset: Int,
    val length: Int,
)

data class LibraryLicense(
    val name: String,
    val terms: String
)