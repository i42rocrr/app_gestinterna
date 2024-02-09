FROM quay.io/p\ypa/manylinux_2_28_x86_64

############# Configuración del SOP
RUN dnf -y install libffi-devel bzip2-devel ed gcc gcc-c++ gcc-gfortran
RUN dnf -y install gzip file fontconfig less libcurl-devel make
RUN dnf -y install openssl openssl-devel readline-devel tar glibc-langpack-en
RUN dnf -y install vi which xz-devel zlib-devel findutils glibc-static
RUN dnf -y install libstdc++ libstdc++-devel libstdc++-static zlib-static
RUN dnf -y install curl tar unzip make patch wget git
RUN dnf -y install gcc-toolset-12-gcc-gfortran openblas-devel openblas
RUN dnf -y install libtiff-devel libjpeg-devel openjpeg2-devel zlib-devel freetype-devel lcms2-devel libwebp-devel
RUN dnf -y install gcc-toolset-9 gcc-toolset-9-gcc-gfortran openblas-devel

RUN dnf -y install epel-release \
   && crb enable \
   && dnf makecache --refresh \
   && dnf module install -y nodejs:18 \
   && dnf -y install /usr/bin/patch
############# Fin configuración del SOP


############ Instalación de GraalVM
ARG GRAALVM_VERSION=21.0.2+13.1
ARG JAVA_VERSION=java20
ARG GRAALVM_PKG=https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-21.0.2/graalvm-community-jdk-21.0.2_linux-x64_bin.tar.gz
ARG TARGETPLATFORM

ENV LANG=en_US.UTF-8 \
    JAVA_HOME=/opt/graalvm-community-openjdk-$GRAALVM_VERSION \
    PATH=/opt/graalvm-community-openjdk-$GRAALVM_VERSION/bin:$PATH

COPY gu-wrapper.sh /usr/local/bin/gu
RUN set -eux \
    && curl --fail --silent --location --retry 3 ${GRAALVM_PKG} \
    | gunzip | tar x -C /opt/ \
    # Set alternative links
    && mkdir -p "/usr/java" \
    && ln -sfT "$JAVA_HOME" /usr/java/default \
    && ln -sfT "$JAVA_HOME" /usr/java/latest \
    && for bin in "$JAVA_HOME/bin/"*; do \
    base="$(basename "$bin")"; \
    [ ! -e "/usr/bin/$base" ]; \
    alternatives --install "/usr/bin/$base" "$base" "$bin" 20000; \
    done \
    && chmod +x /usr/local/bin/gu
############ Fin instalación de GraalVM


############ Instalación de maven
ARG MAVEN_VERSION=3.8.8
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
############ Fin Instalación de maven


######Instalación de GraalPy
ARG GRAALPY_VERSION=23.1.2
ARG GRAALPY_PKG=https://github.com/oracle/graalpython/releases/download/graal-${GRAALPY_VERSION}/graalpy-${GRAALPY_VERSION}-linux-amd64.tar.gz
#ARG GRAALPY_PKG=https://github.com/oracle/graalpython/releases/download/graal-${GRAALPY_VERSION}/graalpy-community-${GRAALPY_VERSION}-linux-amd64.tar.gz
#ARG GRAALPY_PKG=https://github.com/oracle/graalpython/releases/download/graal-${GRAALPY_VERSION}/graalpy-community-jvm-${GRAALPY_VERSION}-linux-amd64.tar.gz
ARG TEMP_REGION=""

RUN set -eux \
    && mkdir -p /graalpy \
    && curl --silent --location --retry 3 $GRAALPY_PKG | gunzip | tar x -C /graalpy --strip-components=1

ENV LANG=es_ES.UTF-8 \
    PATH=/graalpy/bin:$PATH
#############Fin instalación de Graalpy



############## Creación de un entorno virtual de GraalPy##########
RUN graalpy -m venv --system-site-packages --upgrade-deps /graalenv
#RUN graalpy -m venv /graalenv
#RUN /graalpy/bin/graalpy -m venv /graalenv
RUN source /graalenv/bin/activate
############## Fin creación de un entorno virtual de GraalPy############



########### Instalación de paquetes de python en el entorno virtual de GraalPy
RUN  /graalenv/bin/graalpy -m pip install wheel
RUN  /graalenv/bin/graalpy -m pip install setuptools
RUN  /graalenv/bin/graalpy -m pip install psutil
RUN  /graalenv/bin/graalpy -m pip install Cython
RUN  /graalenv/bin/graalpy -m pip install numpy
#RUN  /graalenv/bin/graalpy -m pip install cython
#RUN  /graalenv/bin/graalpy -m pip install python-dateutil
#RUN  /graalenv/bin/graalpy -m pip install pytz
#RUN  /graalenv/bin/graalpy -m pip install tzdata
#RUN  /graalenv/bin/graalpy -m pip install pandas

#RUN  /graalenv/bin/graalpy -m pip install ninja
#RUN  /graalenv/bin/graalpy -m pip install pybind11
#RUN  /graalenv/bin/graalpy -m pip install pillow
#RUN  /graalenv/bin/graalpy -m pip install kiwisolver
######### Fin de instalación de paquetes de python en entorno virtual de GraalPy



############ Compilación y ejecución de la aplicación
COPY . .
CMD [ "mvn", "spring-boot:run" ]
############ Fin Compilación y ejecución de la aplicación