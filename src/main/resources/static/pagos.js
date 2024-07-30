let lbPrecioFinal = 0;

// Ver todos los datos almacenados en localStorage
console.log("Datos en localStorage al cargar:", localStorage);

// Obtener un ítem específico
console.log("Item 'vehicleData':", localStorage.getItem('vehicleData'));

// Función para recuperar y mostrar datos del localStorage
function mostrarDatosVehiculo() {
    const data = JSON.parse(localStorage.getItem('vehicleData'));
    if (data) {
        const lbPlaca = document.getElementById('lbPlaca');
        const lbTipoServicio = document.getElementById('lbTipoServicio');
        const lbMarca = document.getElementById('lbMarca');
        const lbLinea = document.getElementById('lbLinea');
        const lbModelo = document.getElementById('lbModelo');
        const lbChassisNumber = document.getElementById('lbChassisNumber');
        const lbEngineNumber = document.getElementById('lbEngineNumber');
        const lbValorSOAT = document.getElementById('lbValorSOAT');
        const lbPrecio = document.getElementById('lbPrecio');
        const lbButton = document.getElementById('lbButton');

        if (lbPlaca) lbPlaca.innerText = data.plate || 'No disponible';
        if (lbTipoServicio) lbTipoServicio.innerText = data.serviceType || 'No disponible';
        if (lbMarca) lbMarca.innerText = data.brand || 'No disponible';
        if (lbLinea) lbLinea.innerText = data.line || 'No disponible';
        if (lbModelo) lbModelo.innerText = data.model || 'No disponible';
        if (lbChassisNumber) lbChassisNumber.innerText = data.chassisNumber || 'No disponible';
        if (lbEngineNumber) lbEngineNumber.innerText = data.engineNumber || 'No disponible';
        if (lbValorSOAT) lbValorSOAT.innerText = data.tarifaType || 'No disponible';
        if (lbPrecio) lbPrecio.innerText = data.tarifaType || 'No disponible';
        if (lbButton) lbButton.innerText = "Pago PSE " + (data.tarifaType || 'No disponible');

        lbPrecioFinal = lbPrecio;
    }
}

// Función para obtener los medios de pago y agregarlos al <select>
async function obtenerMediosDePago() {
    try {
        const response = await fetch('r5soat-faf3abc78f43.herokuapp.com/pagos/medios', { // URL completa
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        const selectElement = document.getElementById('cbEntidad');
        selectElement.innerHTML = '<option value="" disabled>Selecciona una entidad</option>';

        let nequiValue = ''; // Para almacenar el valor de Nequi

        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id; // Asignar el valor (ID de la entidad)
            option.textContent = item.description; // Asignar el texto a mostrar (Descripción de la entidad)

            if (item.description === 'Nequi') {
                nequiValue = item.id;
            }

            selectElement.appendChild(option);
        });

        if (nequiValue) {
            selectElement.value = nequiValue;
        }

    } catch (error) {
        console.error('Error al obtener medios de pago:', error);
    }
}

// Función para manejar el clic del botón de pago
async function manejarClicBotonPagar() {
    const btnPagar = document.getElementById('btnPagar');

    btnPagar.addEventListener('click', async function (event) {
        event.preventDefault(); // Evita que el formulario se envíe de manera tradicional

        // Obtén los datos del formulario
        const documento = document.getElementById('txtNumeroDocumento').value;
        const telefono = document.getElementById('txtTelefono').value;
        const correo = document.getElementById('txtEmail').value;
        const nombres = document.getElementById('txtNombres').value;
        const medioPagoID = document.getElementById('cbEntidad').value;

        console.log("Datos del formulario:", {
            documento: documento,
            telefono: telefono,
            correo: correo,
            nombres: nombres,
            medioPagoID: medioPagoID
        });

        // Convierte el valor final a BigDecimal si es necesario
        const valorPagoBigDecimal = convertirValorDecimal(lbPrecioFinal ? lbPrecioFinal.innerText : '0');

        // Crear el objeto para actualizar en localStorage
        const vehicleData = JSON.parse(localStorage.getItem('vehicleData')) || {};
        const updatedVehicleData = {
            ...vehicleData,
            documento: documento,
            telefono: telefono,
            correo: correo,
            nombres: nombres
        };

        // Actualizar localStorage
        localStorage.setItem('vehicleData', JSON.stringify(updatedVehicleData));

        console.log("Datos actualizados en localStorage:", localStorage.getItem('vehicleData'));

        // Datos del pago
        const pagoData = {
            documento: documento,
            telefono: telefono,
            correo: correo,
            medioPagoID: medioPagoID,
            valorPagoBigDecimal: valorPagoBigDecimal
        };

        try {
            const response = await fetch('r5soat-faf3abc78f43.herokuapp.com/pago/pse', { // URL completa
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(pagoData)
            });

            if (!response.ok) {
                throw new Error(`Error ${response.status}: ${response.statusText}`);
            }

            // Asegúrate de que la respuesta es JSON
            const result = await response.json();

            // Extraer la URL de la respuesta
            const url = result.transactionDetails.externalResourceUrl;

            // Verificar si la URL está definida antes de intentar abrirla
            if (url) {
                // Abrir la URL en una nueva ventana
                window.open(url, '_blank');
            } else {
                alert('No se encontró una URL de recurso externo.');
            }

        } catch (error) {
            console.error('Error al realizar el pago:', error);
            alert('Error al realizar el pago');
        }
    });

    // Ver todos los datos almacenados en localStorage
    console.log("Datos en localStorage después de manejar clic:", localStorage);

    // Obtener un ítem específico
    console.log("Item 'vehicleData' después de manejar clic:", localStorage.getItem('vehicleData'));
}

// Inicializar las funciones al cargar el DOM
document.addEventListener('DOMContentLoaded', function () {
    mostrarDatosVehiculo();
    obtenerMediosDePago();
    manejarClicBotonPagar();
});

//////////////////////////////////
function convertirValorDecimal(valorTexto) {
    // Eliminar el símbolo de moneda y el separador de miles
    const valorSinSimbolos = valorTexto.replace(/[$.,]/g, '');

    // Convertir el valor a un número entero
    return parseInt(valorSinSimbolos, 10);
}
