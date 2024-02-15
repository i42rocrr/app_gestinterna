import numpy as np
from java.util import ArrayList

def generaArray(listaFarmacosPaciente, listaFarmacosLaboratorio):
    return np.array(list(map(lambda farmaco: farmaco in listaFarmacosPaciente, listaFarmacosLaboratorio)))

def generalListaNombreFarmacosPaciente(elementoPaciente):
    listaNombreFarmacosPaciente=[]
    for farmaco in elementoPaciente.getFarmacosPacientesList():
        listaNombreFarmacosPaciente.append(farmaco.getNombre())
    return listaNombreFarmacosPaciente





laboratorio_vector = np.array(list(map(lambda farmaco: 1, laboratorio.getFarmacosLaboratoriosList())))

listaNombreFarmacosLaboratorio=[]
for farmaco in laboratorio.getFarmacosLaboratoriosList():
    listaNombreFarmacosLaboratorio.append(farmaco.getNombre())

pacientes_vectores = {
    paciente.getNombre(): generaArray(generalListaNombreFarmacosPaciente(paciente), listaNombreFarmacosLaboratorio)
    for paciente in todosPacientes
}


pacientesEncontrados = ArrayList()
for datosPaciente, arrayBooleanoFarmacos in pacientes_vectores.items():
    for elemento in (laboratorio_vector - arrayBooleanoFarmacos):
        if elemento == 0:
            pacientesEncontrados.add(datosPaciente)
