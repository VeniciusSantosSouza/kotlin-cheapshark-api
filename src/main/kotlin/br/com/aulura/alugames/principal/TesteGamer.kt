import org.example.br.com.aulura.alugames.modelo.Gamer

fun main(){

    val gamer1 = Gamer("venicius", "venicius@venicius.com")

    (gamer1)

    val gamer2 = Gamer (
        "venicius",
        "venicius@venicius.com",
        "28-05-1997",
        "vencius")

    //println(gamer2)

    gamer1.let{
        it.dataNascimento = "2026-10-20"
        it.usuario = "jacqueskywalker"
    }.also {
        println(gamer1.idInterno)
    }
    println(gamer1)
    gamer1.usuario = "jacque"
    println(gamer1)
}