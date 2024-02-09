import numpy as np

#Recibo estos arrays
# - todosPacienteFarmacos. Los elementos de los que se compone son:
#      * Pacientes paciente,
#      * List<String> nombreFarmacos
# - todosLaboratorioFarmacos. Los elementos de los que se compone son:
#      * Laboratorios laboratorio,
#      * List<String> nombreFarmacos

#Se va a utilizar el algoritmo de la Distancia Euclídea para hacer el listado "Laboratorio-Pacientes"

#Primer paso: Se crean los grafos correspondientes. Para ello, se transformarán en lista los arays "todosPacienteFarmacos"
# y "todosLaboratorioFarmacos"

farmacos_laboratorio = np.array(list(map(lambda elemento: , laboratorio.getFarmacosLaboratoriosList())))