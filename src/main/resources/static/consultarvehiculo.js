function enviarDatos() {
    // Mostrar la imagen SVG
    document.getElementById('loading').style.display = 'block';
    // Obtener los valores de los inputs
    const placa = document.getElementById('txtPlaca').value;
    const documento = document.getElementById('txtDocumentoIdentidad').value;

    // Construir el objeto con los datos
    const datos = {
        placa: placa,
        documento: documento
    };

    // Realizar la solicitud al backend
    fetch('https://r5soat-faf3abc78f43.herokuapp.com/soat/consultar', { // URL completa
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Éxito:', data);

            // Guardar los datos en localStorage
            localStorage.setItem('vehicleData', JSON.stringify(data));

            // Ocultar la imagen SVG
            document.getElementById('loading').style.display = 'none';

            // Redirigir a la nueva página
            window.location.href = 'pagosPSE.html';
        })
        .catch((error) => {
            // Ocultar la imagen SVG
            document.getElementById('loading').style.display = 'none';
            console.error('Error:', error);
            window.location.href = 'error404.html';
        });
}



function registrar(){
    fetch('https://r5soat-faf3abc78f43.herokuapp.com/soat/ingreso', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}


registrar();