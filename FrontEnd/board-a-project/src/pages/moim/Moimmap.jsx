import React, { useEffect, useRef } from 'react';
import mapboxgl from 'mapbox-gl';

const SEOUL = [126.0, 37.5665];

const MoimMap = () => {
  const mapContainer = useRef(null);

  useEffect(() => {
    mapboxgl.accessToken = import.meta.env.VITE_MAPBOX_ACCESS_TOKEN;
    const map = new mapboxgl.Map({
      container: mapContainer.current,
      style: 'mapbox://styles/mapbox/streets-v11',
      center: SEOUL,
      zoom: 10
    });

    map.on('load', async function () {
      const response = await fetch('https://raw.githubusercontent.com/southkorea/seoul-maps/master/kostat/2013/json/seoul_municipalities_geo_simple.json');
      const data = await response.json();

      // 서울시의 경계를 계산합니다.
      const coordinates = data.features.reduce((acc, feature) => acc.concat(feature.geometry.coordinates), []);
      const bounds = coordinates.reduce((bounds, coord) => {
        return bounds.extend(coord);
      }, new mapboxgl.LngLatBounds(coordinates[0][0], coordinates[0][0]));

      map.dragPan.disable();
      map.scrollZoom.disable();

      // 경계를 기준으로 지도의 뷰포트를 조정합니다.
      map.fitBounds(bounds, { padding: 170 });

      // 'seoul' 소스를 추가합니다.
      map.addSource('seoul', {
        'type': 'geojson',
        'data': data
      });

      data.features.forEach((feature, i) => {
        const layerId = feature.properties.name;

        map.addLayer({
          'id': layerId,
          'type': 'fill',
          'source': 'seoul',
          'paint': {
            'fill-color': '#888',
            'fill-opacity': 0.4
          },
          'filter': ['==', 'name', layerId]
        });

        // 경계선 레이어를 추가합니다.
        map.addLayer({
          'id': layerId + '-outline',
          'type': 'line',
          'source': 'seoul',
          'paint': {
            'line-color': '#000',
            'line-width': 1
          },
          'filter': ['==', 'name', layerId]
        });

        // 텍스트 레이어를 추가합니다.
        map.addLayer({
          'id': layerId + '-label',
          'type': 'symbol',
          'source': 'seoul',
          'layout': {
            'text-field': ['get', 'name'],
            'text-size': 12
          },
          'paint': {
            'text-color': '#000'
          },
          'filter': ['==', 'name', layerId]
        });

        map.on('click', layerId, function (e) {
          console.log(e.features[0].properties.name);
        });
      });
    });

    return () => map.remove();
  }, []);

  return <div ref={mapContainer} style={{ width: '100%', height: '100vh' }} />;
};

export default MoimMap;