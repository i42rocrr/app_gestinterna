import numpy as np


print("Entra en python")

def generaArray(farmacosPaciente, farmacosBuscados):
    return np.array(list(map(lambda farmaco: int(farmaco in farmacosPaciente), farmacosBuscados)))

laboratorio = todosLaboratorios[0]

###### Inicio del "for" -> Caso de un solo laboratorio
#for laboratorio in todosLaboratorios:
farmacos_laboratorio = np.array(list(map(lambda elemento: 1, laboratorio.getFarmacosLaboratoriosList())))

print(farmacos_laboratorio)

farmacos_pacientes = {
    paciente: generaArray(paciente.getFarmacosPacientesList(), laboratorio.getFarmacosLaboratoriosList())
    for paciente in todosPacientes
}

distances = {
    paciente: np.linalg.norm(farmacos_laboratorio - listadoFarmacos)
    for paciente, listadoFarmacos in farmacos_pacientes.items()
}

listaOrdenada = dict(sorted(distances.items(), key=lambda distance: distance[1]))

resultadoPython = list(listaOrdenada.keys())
print(resultadoPython[0].getNombre())
####### Fin del "for"



#print(todosPacientes[0].getNombre())
#print(todosLaboratorios[0].getNombre())