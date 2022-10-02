import Medicos.Interno
import Medicos.Medico
import Medicos.Traumatologo
import Nave.Nave
import Nave.SalaEspera
import Nave.Turno
import java.time.LocalDateTime
import java.util.Date

fun main() {
    var salas: Array<SalaEspera> = arrayOf(
        SalaEspera(ArrayList()), SalaEspera(ArrayList()), SalaEspera(ArrayList())
    )
    var nave = Nave(salas)
    var calendario: Array<Array<Turno?>> = Array(7) { Array(3) { null } }
    crearCalendario(calendario)
    var segundos: Int = 1
    do {
        for (i in calendario.indices) {
            for (j in calendario[0].indices) {
                do {

                    if (segundos % 2 == 0) {
                        nave.asignarSala(Factoria.crearPaciente())
                    }
                    if (segundos % 4 == 0) {
                        var salaEsperaLlena: SalaEspera? = buscarSalaLlena(nave)
                        var pacienteEspera: Paciente? = nave.salas.find { it == salaEsperaLlena }?.sacarPaciente()
                        var medicoTrabajado: Medico? = tratarPaciente(pacienteEspera, calendario[i][j])
                        if (medicoTrabajado != null) {
                            if (pacienteEspera != null) {
                                calendario[i][j]?.registroPacientes?.add(
                                    "Medico: ${medicoTrabajado.NIDI} ha tratado al paciente ${pacienteEspera.NIDI}, que ha venido por ${pacienteEspera.atencionReq}, el dia $i"
                                )
                            }
                        }
                    }
                    segundos++
                } while (segundos % 10 != 0)
            }
        }
    } while (segundos < 210)
    resumir(calendario)
}

fun resumir(calendario: Array<Array<Turno?>>) {
    for (i in calendario.indices){
        for (j in calendario[0].indices){
            for (l in calendario[i][j]?.registroPacientes?.indices!!){
                println(calendario[i][j]?.registroPacientes?.get(l))
            }
        }
    }
}

fun tratarPaciente(pacienteEspera: Paciente?, turno: Turno?): Medico? {
    if (pacienteEspera != null) {
        //Switch segun el tratamiento que necesite
        when (pacienteEspera.atencionReq) {
            //En caso de quemadura
            "Quemadura" -> {
                //Si hay medico que le pueda tratar
                val medico = turno?.medico?.find { it is Traumatologo }
                if (medico != null) {
                    //De su seguro
                    if (turno.medico.find { it == medico }!!.compañias == pacienteEspera.companiaSeg)
                    //Se le trata
                        println("Tratando al paciente ${pacienteEspera.NIDI}")
                    return medico

                } else {
                    //En caso contrario, a otra nave
                    println("El paciente ${pacienteEspera.NIDI} no puede ser tratado")
                    println("Se destinara a otra nave")
                    return medico
                }
            }
            //En caso de impacto
            "Impacto" -> {
                //Si hay medico que le pueda
                val medico = turno?.medico?.find { it is Interno }
                if (medico != null) {
                    //De su seguro
                    if (turno.medico.find { it == medico }!!.compañias == pacienteEspera.companiaSeg)
                    //Se le trata
                        println("Tratando al paciente ${pacienteEspera.NIDI}")
                    return medico
                } else {
                    //En caso contrario, a otra nave
                    println("El paciente ${pacienteEspera.NIDI} no puede ser tratado")
                    println("Se destinara a otra nave")
                    return medico
                }
            }
        }
    }
    return null
}


//Busca la sala con mas gente
fun buscarSalaLlena(nave: Nave): SalaEspera? {
    var salaLlena: SalaEspera? = null
    for (i in nave.salas.indices) {
        if (salaLlena == null) {
            salaLlena = nave.salas[i]
        } else if ((salaLlena.espera.size < nave.salas.size)) {
            salaLlena = nave.salas[i]
        }
    }
    return salaLlena
}

fun crearCalendario(calendario: Array<Array<Turno?>>) {
    for (i in calendario.indices) {
        for (j in calendario[0].indices) {
            when (j) {
                0 -> calendario[i][j] = Factoria.crearTurnoManniana()
                1 -> calendario[i][j] = Factoria.crearTurnoTarde()
                2 -> calendario[i][j] = Factoria.crearTurnoNoche()
            }
        }
    }
}
