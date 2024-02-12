import React, { useEffect, useRef } from "react";
import mapboxgl from "mapbox-gl";
import 'mapbox-gl/dist/mapbox-gl.css';
import { Outlet, useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { locationState } from "../../recoil/atoms/moimState";
import geoData from "../../seoul_geo_simple.json";

const SEOUL = [126.0, 37.5665];

const MoimMap = () => {
  const mapContainer = useRef(null);
  const [location, setLocation] = useRecoilState(locationState);
  const navigate = useNavigate();

  const handleLocationChange = (selectedLocation) => {
    setLocation(selectedLocation);
    navigate("/moim/list");
  };

  useEffect(() => {
    mapboxgl.accessToken = import.meta.env.VITE_MAPBOX_ACCESS_TOKEN;
    const map = new mapboxgl.Map({
      container: mapContainer.current,
      style: "mapbox://styles/mapbox/streets-v11",
      center: SEOUL,
      zoom: 10,
    });

    map.on("load", async function () {
      const data = geoData;

      // 서울시의 경계를 계산합니다.
      const coordinates = data.features.reduce(
        (acc, feature) => acc.concat(feature.geometry.coordinates),
        []
      );
      const bounds = coordinates.reduce((bounds, coord) => {
        return bounds.extend(coord);
      }, new mapboxgl.LngLatBounds(coordinates[0][0], coordinates[0][0]));

      map.dragPan.disable();
      map.scrollZoom.disable();
      map.doubleClickZoom.disable();

      // 경계를 기준으로 지도의 뷰포트를 조정합니다.
      map.fitBounds(bounds, { padding: 170 });

      // 'seoul' 소스를 추가합니다.
      map.addSource("seoul", {
        type: "geojson",
        data: data,
      });

      data.features.forEach((feature, i) => {
        const layerId = feature.properties.name;

        map.addLayer({
          id: layerId,
          type: "fill",
          source: "seoul",
          paint: {
            "fill-color": "#888",
            "fill-opacity": 0.4,
          },
          filter: ["==", "name", layerId],
        });

        // 경계선 레이어를 추가합니다.
        map.addLayer({
          id: layerId + "-outline",
          type: "line",
          source: "seoul",
          paint: {
            "line-color": "#000",
            "line-width": 1,
          },
          filter: ["==", "name", layerId],
        });

        // 텍스트 레이어를 추가합니다.
        map.addLayer({
          id: layerId + "-label",
          type: "symbol",
          source: "seoul",
          layout: {
            "text-field": ["get", "name"],
            "text-size": 12,
          },
          paint: {
            "text-color": "#000",
          },
          filter: ["==", "name", layerId],
        });

        map.on("mousemove", layerId, function () {
          map.getCanvas().style.cursor = "pointer"; // 마우스 커서를 변경합니다.
          map.setPaintProperty(layerId, "fill-color", "#555"); // 레이어의 색상을 변경합니다.
        });

        map.on("mouseleave", layerId, function () {
          map.getCanvas().style.cursor = ""; // 마우스 커서를 기본으로 돌아갑니다.
          map.setPaintProperty(layerId, "fill-color", "#888"); // 레이어의 색상을 원래대로 돌립니다.
        });

        map.on("click", layerId, function (e) {
          console.log(e.features[0].properties.name);
          handleLocationChange("서울시 " + e.features[0].properties.name);
        });
      });
    });

    return () => map.remove();
  }, []);

  return (
    <>
      <div ref={mapContainer} style={{ width: "100%", height: "100vh" }} />
      <Outlet></Outlet>
    </>
  );
};

export default MoimMap;
