@echo off
REM Script para compilar e executar o projeto com Java 21

echo.
echo ========================================
echo REST API com Spring Boot - Java 21
echo ========================================
echo.

REM Configurar JAVA_HOME para Java 21
echo Procurando Java 21...
for /d %%D in (C:\Program Files\Java\*) do (
    if exist "%%D\bin\java.exe" (
        echo Encontrado: %%D
        set JAVA_HOME=%%D
        goto java_found
    )
)

:java_found
if not defined JAVA_HOME (
    echo ERRO: Java 21 nao encontrado!
    echo Por favor, instale Java 21 e tente novamente.
    pause
    exit /b 1
)

echo JAVA_HOME configurado: %JAVA_HOME%
echo.

REM Exibir versao do Java
echo Versao do Java:
%JAVA_HOME%\bin\java -version
echo.

REM Menu de opcoes
echo.
echo Escolha uma opcao:
echo 1. Compilar (mvn clean compile)
echo 2. Instalar (mvn clean install)
echo 3. Executar (mvn spring-boot:run)
echo 4. Compilar e Instalar (mvn clean install)
echo 5. Sair
echo.

set /p choice="Digite a opcao (1-5): "

if "%choice%"=="1" (
    echo.
    echo Compilando projeto...
    mvn clean compile
) else if "%choice%"=="2" (
    echo.
    echo Instalando projeto...
    mvn clean install
) else if "%choice%"=="3" (
    echo.
    echo Executando aplicacao...
    mvn spring-boot:run
) else if "%choice%"=="4" (
    echo.
    echo Compilando e instalando...
    mvn clean install
    echo.
    echo Projeto compilado com sucesso!
    echo Pode executar: mvn spring-boot:run
) else if "%choice%"=="5" (
    echo Saindo...
    exit /b 0
) else (
    echo Opcao invalida!
    pause
    exit /b 1
)

pause

