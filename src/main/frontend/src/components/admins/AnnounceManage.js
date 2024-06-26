import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "../../styles/Form.module.scss";
import {Checkbox} from "../Checkbox";
import {token} from "../../api/axios";
import AnnouncementDeleteModal from "../AnnouncementDeleteModal";

function AnnounceManage() {
    const [announcements, setAnnouncements] = useState([]);
    const [checkedState, setCheckedState] = useState({});
    const [isAllChecked, setIsAllChecked] = useState(false);
    const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false); // 삭제 모달 상태
    const [selectedAnnouncement, setSelectedAnnouncement] = useState(null); // 선택된 공지
    const navigate = useNavigate();



    // 삭제 모달 열기
    const openDeleteModal = (announcement) => {
        setSelectedAnnouncement(announcement);
        setIsDeleteModalOpen(true);
    };

    // 삭제 모달 닫기
    const closeDeleteModal = () => {
        setSelectedAnnouncement(null);
        setIsDeleteModalOpen(false);
    };

    // 삭제 모달에서 삭제 확인 시
    const handleDeleteAnnouncement = async () => {
        try {
            await token.delete(`/api/announcements/${selectedAnnouncement.id}`);
            // 삭제 후 상태 업데이트 및 모달 닫기
            setAnnouncements(announcements.filter(announce => announce.id !== selectedAnnouncement.id));
            closeDeleteModal();
        } catch (error) {
            console.error("공지 삭제 실패", error);
        }
    };




    useEffect(() => {
        const fetchAnnouncements = async () => {
            try {
                const response = await token.get('/api/announcements');
                setAnnouncements(response.data);
                const newCheckedState = {};
                response.data.forEach(announce => {
                    newCheckedState[announce.id] = false;
                });
                setCheckedState(newCheckedState);
            } catch (error) {
                console.error("공지사항을 불러오는 데 실패했습니다.", error);
            }
        };
        fetchAnnouncements();
    }, []);

    const handleCreateButtonClick = () => {
        navigate('/announcement-create');
    };

    // 편집 페이지로 넘어가는 함수
    const handleEditButtonClick = (id) => {
        console.log("id!!:", id);
        navigate(`/announcement-edit/${id}`, { replace: true });
    };

    const handleCheckboxChange = (id) => {
        const newState = { ...checkedState, [id]: !checkedState[id] };
        setCheckedState(newState);
        setIsAllChecked(Object.values(newState).every(state => state));
    };

    const handleAllCheckboxes = () => {
        const newState = Object.keys(checkedState).reduce((acc, key) => {
            acc[key] = !isAllChecked;
            return acc;
        }, {});
        setCheckedState(newState);
        setIsAllChecked(!isAllChecked);
    };




    return(
        <div className="container" style={{padding: '0px'}}>
            <div className="admin-menu-area" style={{width: '100%'}}>
                <div className="top-title-area" style={{marginTop: '20px', padding: '10px', width: '100%', height: '40px', borderBottom: '1px solid #c0c0c0', fontSize: '22px', fontWeight: 'bold', color: '#808000'}}>공지 관리</div>

                <div className="second-title" style={{marginTop: '10px', padding: '20px', width: '100%', height: '40px'}}>
                    <div style={{marginLeft: '30px', fontSize: '20px', fontWeight: 'bold', color: '#808000'}}>공지 리스트</div>
                </div>

                <div className="content-area" style={{marginTop: '10px', padding: '10px', width: '100%'}}>
                    <div className="create">
                        <button className="category-create" onClick={handleCreateButtonClick}>공지사항 등록
                        </button>
                    </div>

                    <table className={styles.categoryTable}>
                        <thead>
                        <tr>
                        <th style={{width: "10%"}}><Checkbox checked={isAllChecked} onChange={handleAllCheckboxes}/>
                            </th>
                            <th style={{width: "30%"}}>제목</th>
                            <th style={{width: "25%"}}>등록일</th>
                            <th style={{width: "25%"}}>수정일</th>
                            <th style={{width: "10%"}}>관리</th>
                        </tr>
                        </thead>
                        <tbody>
                        {announcements.map(announce => (
                            <tr key={announce.id}>
                                <td><Checkbox checked={checkedState[announce.id]} onChange={() => handleCheckboxChange(announce.id)}/></td>
                                <td>{announce.title}</td>
                                <td>{new Date(announce.createdAt).toLocaleDateString()}</td>
                                <td>{new Date(announce.updatedAt).toLocaleDateString()}</td>
                                <td className={styles.editOptions}>
                                    <img src="/img/icon/Lucide.png" alt="관리아이콘"/>
                                    <div className={styles.editIcons}>
                                        <button className={styles.edit}
                                                onClick={() => handleEditButtonClick(announce.id)}>편집
                                        </button>
                                        {/* 삭제 버튼 클릭 시 삭제 모달 열기 */}
                                        <button className={styles.delete} onClick={() => openDeleteModal(announce)}>삭제</button>
                                    </div>

                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>

                </div>

            </div>
            {/* 삭제 모달 */}
            <AnnouncementDeleteModal
                isOpen={isDeleteModalOpen}
                onClose={closeDeleteModal}
                announcement={selectedAnnouncement}
                onDeleteAnnouncement={handleDeleteAnnouncement}
            />
        </div>
    );

}

export default AnnounceManage;