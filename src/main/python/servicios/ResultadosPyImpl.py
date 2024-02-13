import numpy as np

def generaArray(listaFarmacosPaciente, listaFarmacosLaboratorio):
    return np.array(list(map(lambda farmaco: farmaco in listaFarmacosPaciente, listaFarmacosLaboratorio)))

def generalListaNombreFarmacosPaciente(elementoPaciente):
    listaNombreFarmacosPaciente=[]
    for farmaco in elementoPaciente.getFarmacosPacientesList():
        listaNombreFarmacosPaciente.append(farmaco.getNombre())
    return listaNombreFarmacosPaciente

for laboratorio in todosLaboratorios:
    laboratorio_vector = np.array(list(map(lambda farmaco: laboratorio.getNombre(), laboratorio.getFarmacosLaboratoriosList())))


    listaNombreFarmacosLaboratorio=[]
    for farmaco in laboratorio.getFarmacosLaboratoriosList():
        listaNombreFarmacosLaboratorio.append(farmaco.getNombre())

    pacientes_vectores = {
        paciente.getNombre(): generaArray(generalListaNombreFarmacosPaciente(paciente), listaNombreFarmacosLaboratorio)
        for paciente in todosPacientes
    }

