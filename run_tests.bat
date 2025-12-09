@echo off
REM Script para ejecutar las pruebas unitarias del Proyecto de Agencia de Viajes
REM Este script compila y ejecuta todas las pruebas unitarias

setlocal enabledelayedexpansion

set JAVA_HOME=C:\Program Files\Java
set PROJECT_DIR=%cd%
set BUILD_DIR=%PROJECT_DIR%\build
set TEST_DIR=%PROJECT_DIR%\test
set LIB_DIR=%PROJECT_DIR%\lib
set CLASSPATH=%BUILD_DIR%\classes;%LIB_DIR%\junit-4.13.2.jar;%LIB_DIR%\hamcrest-core-1.3.jar;%LIB_DIR%\mockito-core-5.13.0.jar;%LIB_DIR%\byte-buddy-1.15.1.jar;%LIB_DIR%\byte-buddy-agent-1.15.1.jar

echo.
echo ========================================
echo  TESTS DEL PROYECTO DE AGENCIA DE VIAJES
echo ========================================
echo.

REM Crear directorios si no existen
if not exist "%BUILD_DIR%\test" mkdir "%BUILD_DIR%\test"

REM Compilar código fuente si no está compilado
if not exist "%BUILD_DIR%\classes\Controllers" (
    echo Compilando código fuente...
    javac -cp "%LIB_DIR%\*" -d "%BUILD_DIR%\classes" src\Models\*.java src\DataAccess\*.java src\Controllers\*.java
    if !errorlevel! neq 0 (
        echo Error al compilar el código fuente
        exit /b 1
    )
    echo.
)

REM Compilar tests
echo Compilando pruebas unitarias...
javac -cp "%CLASSPATH%" -d "%BUILD_DIR%\test" test\java\Models\*.java test\java\Controllers\*.java
if !errorlevel! neq 0 (
    echo Error al compilar las pruebas
    exit /b 1
)
echo Pruebas compiladas correctamente
echo.

REM Ejecutar tests
echo Ejecutando pruebas unitarias...
echo.

REM Tests de Modelos
echo ====== TESTS DE MODELOS ======
java -cp "%CLASSPATH%;%BUILD_DIR%\test" org.junit.runner.JUnitCore Models.UsuarioTest Models.ClienteTest
if !errorlevel! neq 0 (
    echo Error en tests de Modelos
)
echo.

REM Tests de Controllers
echo ====== TESTS DE CONTROLLERS ======
java -cp "%CLASSPATH%;%BUILD_DIR%\test" org.junit.runner.JUnitCore Controllers.UsuarioControllerTest Controllers.ClienteControllerTest
if !errorlevel! neq 0 (
    echo Error en tests de Controllers
)
echo.

echo ========================================
echo  TESTS COMPLETADOS
echo ========================================
echo.

pause
