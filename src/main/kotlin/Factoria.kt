import Nave.Horario
import Nave.Turno

object Factoria {
    fun crearPaciente(): Paciente =
        Paciente(20, "nombre1", "Sanitroopers", "otros", 1)
    fun crearPacienteQuemado(): Paciente =
        Paciente(20, "nombre1", "Sanitroopers", "Quemadura", 1)
    fun crearPacienteImpactado(): Paciente =
        Paciente(20, "nombre1", "Sanitroopers", "Impacto", 1)
    fun crearTurnoManniana(): Turno = Turno(Horario.MANNIANA, ArrayList(), ArrayList())
    fun crearTurnoTarde(): Turno = Turno(Horario.TARDE, ArrayList(), ArrayList())
    fun crearTurnoNoche(): Turno = Turno(Horario.NOCHE, ArrayList(), ArrayList())
}