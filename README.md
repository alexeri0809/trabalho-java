Comando para inicializar (obs: tem de estar dentro da pasta matriculas-carro)

java -cp target\classes pt.exemplo.matriculas.Main

Caso dê erro, tente estes comandos:

# Limpar classes antigas
Remove-Item -Recurse -Force target\classes -ErrorAction SilentlyContinue
mkdir target\classes -Force

# Compilar
javac -d target\classes (Get-ChildItem -Recurse -Filter *.java -Path src\main\java).FullName
