
//formatage de montant

export function addScript() {
    console.log('charger')
    var script = document.createElement('script');
    script.type = 'application/javascript';
    script.src = 'https://raw.githack.com/eKoopmans/html2pdf/master/dist/html2pdf.bundle.js';
    document.head.appendChild(script);
}