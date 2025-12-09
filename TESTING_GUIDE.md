# GUÍA DE PRUEBAS UNITARIAS

## ¿Cómo ejecutar las pruebas?

### Opción 1: Windows (Más fácil)
1. Abre el explorador de archivos
2. Navega a la carpeta raíz del proyecto `TravelAgencyProject`
3. Haz doble clic en el archivo `run_tests.bat`
4. Las pruebas se ejecutarán automáticamente

### Opción 2: Línea de comandos PowerShell
```powershell
cd C:\Users\juank\OneDrive\Desktop\"Proyectos U"\TravelAgencyProject
.\run_tests.bat
```

### Opción 3: Ejecución manual con Java
```bash
# Navega a la carpeta del proyecto
cd C:\Users\juank\OneDrive\Desktop\Proyectos\ U\TravelAgencyProject

# Compila el código fuente (si no está compilado)
javac -cp "lib/*" -d build/classes src/Models/*.java src/DataAccess/*.java src/Controllers/*.java

# Compila las pruebas
javac -cp "build/classes;lib/*" -d build/test test/java/Models/*.java test/java/Controllers/*.java

# Ejecuta las pruebas
java -cp "build/classes;build/test;lib/*" org.junit.runner.JUnitCore Models.UsuarioTest Models.ClienteTest Controllers.UsuarioControllerTest Controllers.ClienteControllerTest
```

---

## ¿Qué se prueba?

### Pruebas de Modelos (19 pruebas)
Se validan las clases de modelo para asegurar que:
- ✅ Los constructores funcionan correctamente
- ✅ Los getters/setters establecen y obtienen valores correctamente
- ✅ La herencia de clases (Cliente extends Usuario) funciona
- ✅ El método toString() genera salida válida

**Clases probadas:**
- `Models.Usuario` - 10 pruebas
- `Models.Cliente` - 9 pruebas

### Pruebas de Controladores (28 pruebas)
Se validan las operaciones CRUD y las validaciones de entrada:

**UsuarioController (17 pruebas):**
- ✅ Agregar Usuario con validaciones
- ✅ Actualizar Usuario existente
- ✅ Eliminar Usuario
- ✅ Obtener Usuario por ID
- ✅ Manejo de datos inválidos (null, vacíos, espacios)

**ClienteController (11 pruebas):**
- ✅ Agregar Cliente con validaciones
- ✅ Validación de existencia de Usuario
- ✅ Actualizar Cliente
- ✅ Eliminar Cliente
- ✅ Obtener Cliente por ID

---

## Validaciones que se comprueban

### Campos Obligatorios (no pueden ser null o vacíos)
✅ Usuario: nombre, apellido, correo, rol
✅ Cliente: usuarioId (y usuario debe existir)

### Campos Opcionales (pueden estar vacíos)
✅ Teléfono
✅ Contraseña
✅ Preferencias de Cliente

### Comportamientos esperados
✅ Los espacios en blanco se recortan automáticamente
✅ Las operaciones retornan booleanos indicando éxito/fallo
✅ Los datos inválidos se rechazan sin guardar
✅ Las transacciones se realizan correctamente

---

## Interpretación de Resultados

### Resultado Exitoso
```
JUnit version 4.13.2
.......................
Time: 1.234

OK (47 tests)
```
✅ Todos los puntos (.) significan pruebas que pasaron
✅ "OK" indica que no hubo fallos

### Resultado con Fallos
```
JUnit version 4.13.2
...F...E...

FAILURES!!!
Tests run: 12,  Failures: 1, Errors: 1
```
❌ "F" indica un test que falló
❌ "E" indica un error en la prueba

---

## Estructura de las pruebas

Las pruebas están organizadas en:

```
test/
├── java/
│   ├── Models/
│   │   ├── UsuarioTest.java
│   │   └── ClienteTest.java
│   └── Controllers/
│       ├── UsuarioControllerTest.java
│       └── ClienteControllerTest.java
```

### Ejemplo de una prueba simple:
```java
@Test
public void testAddUsuarioConDatosValidos() {
    // Arrange (Preparar datos)
    Usuario usuarioMock = mock(UsuarioRepository.class);
    UsuarioController controller = new UsuarioController(usuarioMock);
    
    // Act (Ejecutar operación)
    boolean resultado = controller.addUsuario("Juan", "Mora", "juan@mail.com", "123", "cliente", "pass");
    
    // Assert (Verificar resultado)
    assertTrue("El usuario debería agregarse correctamente", resultado);
}
```

---

## Solución de problemas

### Problema: "No se encuentra junit"
**Solución:** Verifica que los archivos .jar estén en la carpeta `lib/`:
- junit-4.13.2.jar
- hamcrest-core-1.3.jar
- mockito-core-5.13.0.jar
- byte-buddy-1.15.1.jar
- byte-buddy-agent-1.15.1.jar

### Problema: "No se puede compilar"
**Solución:** Asegúrate de que:
1. Java esté instalado correctamente (javac debe estar en el PATH)
2. La carpeta `build/classes` exista (se crea automáticamente)
3. Los archivos fuente estén en las carpetas correctas

### Problema: Tests fallan por "Cannot mock this class"
**Solución:** Esto puede ocurrir con Java 23. Asegúrate de que byte-buddy sea versión 1.15.1 o posterior.

---

## Agregar nuevas pruebas

Para agregar pruebas para un nuevo Controller:

1. Crea un archivo `NuevoControllerTest.java` en `test/java/Controllers/`
2. Usa el patrón de las pruebas existentes
3. Importa las clases necesarias:
```java
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
```

4. Compila las nuevas pruebas:
```bash
javac -cp "build/classes;lib/*" -d build/test test/java/Controllers/NuevoControllerTest.java
```

5. Ejecuta:
```bash
java -cp "build/classes;build/test;lib/*" org.junit.runner.JUnitCore Controllers.NuevoControllerTest
```

---

## Referencias

- **JUnit 4:** https://junit.org/junit4/
- **Mockito:** https://site.mockito.org/
- **Java Testing Best Practices:** https://www.oracle.com/java/technologies/

---

**Última actualización:** 8 de Diciembre de 2025
**Estado:** Todas las pruebas pasando ✅
