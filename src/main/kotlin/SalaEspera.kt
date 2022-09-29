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

}