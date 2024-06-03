import React, { useState, useEffect, useContext } from 'react';
import Navbar from "../../components/navbar";
import "./styles.css";
import { Link } from 'react-router-dom';
import { AuthenticationContext, LoadingContext } from "../../config/context";
import { apis, endpoints } from '../../config/apis';

const ManageCompany = () => {
    const [selectedOption, setSelectedOption] = useState('month');
    const [date, setDate] = useState('');
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [message, setMessage] = useState('');
    const [companyId, setCompanyId] = useState(null);
    const { setLoading } = useContext(LoadingContext);
    const { accessToken, user } = useContext(AuthenticationContext);

    useEffect(() => {
        const fetchCompanyId = async () => {
            try {
                setLoading(true);
                const api = apis(accessToken);
                const response = await api.get(endpoints.get_company_managerid(user.id));
                setCompanyId(response.data.id);
            } catch (error) {
                console.error('Error fetching company ID:', error);
            } finally {
                setLoading(false);
            }
        };

        if (user && user.id) {
            fetchCompanyId();
        }
    }, [user, accessToken, setLoading]);

    const renderChart = () => {
        return (
            <div className="chart-placeholder">
                <h2>Chart Placeholder</h2>
                <p>Thống kê theo {selectedOption} vào ngày {date}</p>
            </div>
        );
    };

    const handleRegisterCargo = async () => {
        setShowConfirmation(false); // Tắt hộp thoại xác nhận trước khi gọi API
        try {
            setLoading(true);
            const api = apis(accessToken);
            const response = await api.put(endpoints.register_cargo(companyId), { date });
            if (response.status === 200) {
                alert('Registration successful!');
            } else {
                alert('Registration failed!');
            }
        } catch (error) {
            console.error('Error registering cargo:', error);
            alert('An error occurred during registration.');
        } finally {
            setLoading(false);
        }
    };

    const showConfirmationDialog = () => {
        setShowConfirmation(true);
    };

    const hideConfirmationDialog = () => {
        setShowConfirmation(false);
    };

    return (
        <>
            <Navbar />
            <div className="button-container">
                <Link to="/create-route">
                    <button>Create Route</button>
                </Link>
                <Link to="/register-trip">
                    <button>Register Trip</button>
                </Link>
                <button onClick={showConfirmationDialog}>Register Cargo</button>
            </div>
            {showConfirmation && (
                <div className="confirmation-overlay">
                    <div className="confirmation-dialog">
                        <p>Are you sure you want to register cargo transport?</p>
                        <button onClick={handleRegisterCargo}>OK</button>
                        <button onClick={hideConfirmationDialog}>Cancel</button>
                    </div>
                </div>
            )}
            <div className="stats-chart-container">
                <div className="stats-button-container">
                    <button onClick={() => setSelectedOption('month')}>Thống kê theo tháng</button>
                    <button onClick={() => setSelectedOption('quarter')}>Thống kê theo quý</button>
                    <button onClick={() => setSelectedOption('day')}>Thống kê theo ngày</button>
                    <input 
                        type="date" 
                        value={date} 
                        onChange={(e) => setDate(e.target.value)} 
                        className="date-input"
                    />
                </div>
                <div className="chart-container">
                    {renderChart()}
                </div>
            </div>
        </>
    );
};

export default ManageCompany;
