package Nave

import Paciente

class SalaEspera(var espera: ArrayList<Paciente>) {
    fun meterPaciente(paciente: Paciente) {
        if (espera.isNotEmpty()) {
            if (paciente.prioridadPac == 3) {
                espera.add((espera.size - 1), paciente)
            } else {
                for (i in espera.indices) {
                    if (espera[i].prioridadPac < paciente.prioridadPac) {
                        espera.add((i - 1), paciente)
                    }
                }
            }
        } else {
            espera.add(paciente)
        }
    }

    fun sacarPaciente(): Paciente? {
        try {
            return espera.removeFirst()
        } catch (e: NoSuchElementException) {
            println("No quedan pacientes en la sala de espera")
        }
        return null
    }

}