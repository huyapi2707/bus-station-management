import React, { useState, useContext } from 'react';
import './styles.css';
import { apis, endpoints } from '../../config/apis';
import { AuthenticationContext, LoadingContext } from '../../config/context';
import { useNavigate } from 'react-router-dom';

const CreateCompany = () => {
  const { accessToken, user } = useContext(AuthenticationContext);
  const { setLoading } = useContext(LoadingContext);
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    phone: '',
    email: '',
    isCargoTransport: false,
    isActive: true,
    isVerified: false,
    managerId: user ? user.id : ''
  });
  const [avatarPreview, setAvatarPreview] = useState(null);
  const [isSubmitted, setIsSubmitted] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setAvatarPreview(e.target.result);
      };
      reader.readAsDataURL(file);
    } else {
      setAvatarPreview(null);
    }
  };

  const handleSubmit = async () => {
    setLoading('flex');
    const data = new FormData();
    data.append('company', new Blob([JSON.stringify(formData)], { type: 'application/json' }));
    if (document.getElementById('avatar').files[0]) {
      data.append('avatar', document.getElementById('avatar').files[0]);
    }

    try {
      const api = apis(accessToken);
      await api.post(endpoints.register_company, data, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      setIsSubmitted(true);
    } catch (error) {
      console.error("Failed to create new company:", error);
      alert("Failed to create new company");
    } finally {
      setLoading('none');
    }
  };

  return (
    <div className="form-container">
      <h2>Register your Bus Company</h2>
      {!isSubmitted ? (
        <form id="newCompanyForm" encType="multipart/form-data">
          <label htmlFor="name">Name:</label>
          <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} required />

          <label htmlFor="phone">Phone:</label>
          <input type="text" id="phone" name="phone" value={formData.phone} onChange={handleChange} required />

          <label htmlFor="email">Email:</label>
          <input type="email" id="email" name="email" value={formData.email} onChange={handleChange} required />

          <label htmlFor="isCargoTransport">Is Cargo Transport:</label>
          <input type="checkbox" id="isCargoTransport" name="isCargoTransport" checked={formData.isCargoTransport} onChange={handleChange} />

          <label htmlFor="avatar">Avatar:</label>
          <input type="file" id="avatar" name="avatar" accept="image/*" onChange={handleAvatarChange} />
          {avatarPreview && <img id="avatarPreview" src={avatarPreview} alt="Avatar Preview" style={{ maxWidth: '150px', marginTop: '10px' }} />}

          <button type="button" onClick={handleSubmit}>Save</button>
          <button type="button" className="cancel" onClick={() => navigate('/')}>Cancel</button>
        </form>
      ) : (
        <div className="submission-message">
          <p>Your registration has been submitted. Please wait for the administrator to approve it.</p>
          <button type="button" className="btn btn-primary" onClick={() => navigate('/')}>
            Go back
          </button>
        </div>
      )}
    </div>
  );
};

export default CreateCompany;
