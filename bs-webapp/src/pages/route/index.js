import React, { useState, useEffect, useContext } from 'react';
import './styles.css';
import Navbar from "../../components/navbar";
import { apis, endpoints } from '../../config/apis';
import { LoadingContext, AuthenticationContext } from '../../config/context';
import { useNavigate } from 'react-router-dom';

const CreateRoute = () => {
    const [stations, setStations] = useState([]);
    const [formData, setFormData] = useState({
        fromStation: '',
        toStation: '',
        routeName: '',
        seatPrice: '',
        cargoPrice: '',
        isActive: true,
        companyId: ''  // Mặc định để trống, sẽ được nạp khi fetch company thành công
    });

    const { setLoading } = useContext(LoadingContext);
    const { accessToken, user } = useContext(AuthenticationContext);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchCompany = async () => {
            try {
                setLoading(true);
                const api = apis(accessToken);
                const response = await api.get(endpoints.get_company_managerid(user.id)); 
                console.log("Company data received:", response.data);
                console.log("Fetched companyId:", response.data.id); // In ra companyId
                setFormData((prevData) => ({
                    ...prevData,
                    companyId: response.data.id
                }));
            } catch (error) {
                console.error('Error fetching company', error);
            } finally {
                setLoading(false);
            }
        };

        if (user && user.id) {
            fetchCompany();
        }
    }, [user, accessToken, setLoading]);

    useEffect(() => {
        const fetchStations = async () => {
            try {
                setLoading(true);
                const api = apis(accessToken);
                const response = await api.get(endpoints.list_station);
                console.log("Stations data received:", response.data);
                setStations(response.data);
            } catch (error) {
                console.error('Error fetching stations', error);
            } finally {
                setLoading(false);
            }
        };

        fetchStations();
    }, [accessToken, setLoading]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            setLoading(true);
            const api = apis(accessToken);
            const routeData = {
                name: formData.routeName,
                companyId: formData.companyId,
                seatPrice: formData.seatPrice,
                cargoPrice: formData.cargoPrice,
                fromStationId: formData.fromStation,
                toStationId: formData.toStation,
                isActive: formData.isActive
            };
            console.log("Sending route data:", routeData);
            await api.post(endpoints.create_route, routeData);
            alert('Route created successfully');
            navigate(-1);
        } catch (error) {
            console.error('Error creating route', error);
            alert('Failed to create route');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <Navbar />
            <form onSubmit={handleSubmit} className="route-form">
                <div className="form-group">
                    <label>Nơi đi:</label>
                    <select name="fromStation" value={formData.fromStation} onChange={handleChange}>
                        <option value="">Chọn nơi đi</option>
                        {stations && stations.map(station => (
                            <option key={station.id} value={station.id}>{station.address}</option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Nơi đến:</label>
                    <select name="toStation" value={formData.toStation} onChange={handleChange}>
                        <option value="">Chọn nơi đến</option>
                        {stations && stations.map(station => (
                            <option key={station.id} value={station.id}>{station.address}</option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label>Tên tuyến:</label>
                    <input type="text" name="routeName" value={formData.routeName} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Giá ghế:</label>
                    <input type="number" name="seatPrice" value={formData.seatPrice} onChange={handleChange} />
                </div>
                <div className="form-group">
                    <label>Giá vận chuyển hàng:</label>
                    <input type="number" name="cargoPrice" value={formData.cargoPrice} onChange={handleChange} />
                </div>
                <button type="submit">Tạo tuyến</button>
            </form>
        </div>
    );
};

export default CreateRoute;
