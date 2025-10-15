/**
 * main-app.js
 *
 * Proyecto principal que utiliza la calculadora y la librería de consola.
 */

// 1. Importación de ambas Librerías
const CalculadoraImpuestos = require('./tax-calculator-lib');
const Consola = require('./console-input-lib');

/**
 * Función principal asíncrona que ejecuta la aplicación de la calculadora.
 */
async function ejecutarCalculadora() {
    let continuar = true;

    // Bucle principal de la aplicación
    while (continuar) {
        // Obtenemos la selección del usuario usando la librería de consola
        const opcion = await Consola.mostrarYSeleccionarOperacion();
        
        if (opcion === 4) {
            continuar = false; // Opción para salir
            console.log('\n👋 Gracias por usar la Calculadora de Impuestos. ¡Adiós!');
            break;
        }

        console.log('---------------------------------------');
        
        let montoBase = null;
        let esNumero = false;

        // Bucle para solicitar un monto válido
        while (!esNumero) {
            const respuestaMonto = await Consola.preguntar('Ingrese el Monto Base o Subtotal: $');
            montoBase = parseFloat(respuestaMonto);
            
            if (!isNaN(montoBase) && montoBase > 0) {
                esNumero = true;
            } else {
                console.log('⚠️ Entrada no válida. Por favor, ingrese un número positivo.');
            }
        }

        let resultado = 0;
        let etiqueta = '';
        
        // Ejecución de la operación seleccionada
        switch (opcion) {
            case 1: // Calcular solo IVA
                resultado = CalculadoraImpuestos.calcularIVA(montoBase);
                etiqueta = `IVA (tasa ${CalculadoraImpuestos.TASA_IVA_GENERAL * 100}%)`;
                break;

            case 2: // Calcular Total con IVA
                resultado = CalculadoraImpuestos.calcularTotalConIVA(montoBase);
                etiqueta = 'TOTAL con IVA incluido';
                break;

            case 3: // Calcular Impuesto Específico (ICE)
                resultado = CalculadoraImpuestos.calcularICE(montoBase);
                etiqueta = `ICE (tasa ${CalculadoraImpuestos.TASA_ICE_ESPECIFICO * 100}%)`;
                break;
        }

        console.log(`\n✅ Monto Base: $${montoBase.toFixed(2)}`);
        console.log(`Resultado del cálculo (${etiqueta}): $${resultado.toFixed(2)}`);
        console.log('=======================================\n');
    }

    // Aseguramos el cierre de la interfaz de lectura al terminar
    Consola.cerrar();
}

// Inicializar la aplicación
ejecutarCalculadora();