package biz.ei6.plages.services

import android.content.Context
import biz.ei6.plages.Plage
import java.io.*
import java.util.*

fun creeOuExiste(contexte : Context) : File {
    val fichier = File(contexte.filesDir,"plages.txt")
    if(!fichier.exists()) fichier.createNewFile()
    return fichier
}

fun sauvegarde(contexte : Context,plage : Plage) {

    FileOutputStream(creeOuExiste(contexte),true).use {
        PrintWriter(it).apply{
            println("${plage.nom},${plage.description},${plage.image},${plage.url}," +
                    "${plage.largeur},${plage.longueur},${plage.latitude},${plage.longitude}")
            close()
        }
    }
}

fun charge(contexte : Context) : MutableList<Plage> {
    val ret = mutableListOf<Plage>()
    FileReader(creeOuExiste(contexte)).forEachLine {
       val str =  StringTokenizer (it,",")
       ret.add(Plage(str.nextToken(),str.nextToken(),str.nextToken(),str.nextToken(),
           str.nextToken().toInt(),str.nextToken().toInt(),
           str.nextToken().toDouble(),str.nextToken().toDouble()))
    }

    return ret
}