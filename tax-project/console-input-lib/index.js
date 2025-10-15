/**
 * console-input-lib/index.js
 *
 * Módulo de la biblioteca para manejar la entrada de datos por consola.
 */

const readline = require('readline');

// Creamos la interfaz de lectura/escritura (stdin/stdout)
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

/**
 * Función genérica para preguntar algo al usuario por consola.
 * Utiliza Promises para manejar la entrada de forma asíncrona.
 *
 * @param {string} pregunta - El texto de la pregunta a mostrar.
 * @returns {Promise<string>} Una Promesa que se resuelve con la respuesta del usuario.
 */
function preguntar(pregunta) {
    return new Promise(resolve => {
        rl.question(pregunta, (respuesta) => {
            resolve(respuesta.trim());
        });
    });
}

/**
 * Muestra el menú de operaciones y pide al usuario que seleccione una opción.
 *
 * @returns {Promise<number>} Una Promesa que se resuelve con el número de la opción seleccionada.
 */
async function mostrarYSeleccionarOperacion() {
    console.log('\n=======================================');
    console.log('   CALCULADORA DE IMPUESTOS (Menú)');
    console.log('=======================================');
    console.log('1. Calcular solo IVA (15%)');
    console.log('2. Calcular Total con IVA');
    console.log('3. Calcular Impuesto Específico (ICE)');
    console.log('4. Salir');
    console.log('---------------------------------------');

    let opcion = null;
    let esValido = false;

    // Bucle para asegurar que el usuario ingrese una opción válida (1, 2, 3 o 4)
    while (!esValido) {
        const respuesta = await preguntar('Seleccione una opción (1-4): ');
        opcion = parseInt(respuesta);

        if (opcion >= 1 && opcion <= 4 && !isNaN(opcion)) {
            esValido = true;
        } else {
            console.log('❌ Opción no válida. Por favor, ingrese un número del 1 al 4.');
        }
    }

    return opcion;
}

/**
 * Cierra la interfaz de lectura de consola. Debe llamarse al finalizar la aplicación.
 */
function cerrar() {
    rl.close();
}

// Exportamos las funciones esenciales para que el proyecto principal las use.
module.exports = {
    preguntar,
    mostrarYSeleccionarOperacion,
    cerrar
};