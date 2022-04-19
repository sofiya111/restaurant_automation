ymaps.ready(init);
function init () {
    var myMap = new ymaps.Map('map', {
            center: [54.62935942113811,39.71931088360592],
            zoom: 14,
            controls: []
        }, {
            searchControlProvider: 'yandex#search'
        }),
        objectManager = new ymaps.ObjectManager({
            clusterize: true,
            gridSize: 32,
            clusterDisableClickZoom: true
        });
    objectManager.objects.options.set('preset', 'islands#redDotIcon');
    objectManager.clusters.options.set('preset', 'islands#redClusterIcons');
    myMap.geoObjects.add(objectManager);

    $.ajax({
        url: "/static/data.json"
    }).done(function(data) {
        objectManager.add(data);
    });

}