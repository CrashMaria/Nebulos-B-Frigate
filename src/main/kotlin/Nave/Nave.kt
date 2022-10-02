package Nave

import Paciente
import java.util.Random

class Nave(var salas: Array<SalaEspera>) {
    fun asignarSala(paciente: Paciente) {
        if (salas[0].espera.size != salas[1].espera.size || salas[1].espera.size != salas[2].espera.size) {
            var salaMenos: SalaEspera = salas[0]
            for (i in salas.indices) {
                if (salas[i].espera.size < salaMenos.espera.size) {
                    salaMenos = salas[i]
                }
            }
            salas.find { it == salaMenos }?.meterPaciente(paciente)
        } else {
            var rand: Int
            rand= Random().nextInt(3)
            salas[rand].meterPaciente(paciente)
        }
    }
}
